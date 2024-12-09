package com.marcelm.informtechtask.application.service

import com.marcelm.informtechtask.application.port.persistence.ReadRepairPort
import com.marcelm.informtechtask.application.port.persistence.WriteRepairPort
import com.marcelm.informtechtask.application.usecase.repair.RepairFindAllUseCase
import com.marcelm.informtechtask.application.usecase.repair.RepairFindByOwnerUseCase
import com.marcelm.informtechtask.application.usecase.repair.RepairFinishUseCase
import com.marcelm.informtechtask.application.usecase.repair.RepairStartUseCase
import com.marcelm.informtechtask.application.usecase.repair.StartOrStopRepairUseCase
import com.marcelm.informtechtask.application.usecase.repair.SubmitRepairCreateUseCase
import com.marcelm.informtechtask.application.usecase.repair.SubmitRepairDeleteUseCase
import com.marcelm.informtechtask.application.usecase.repair.SubmitRepairUpdateUseCase
import com.marcelm.informtechtask.domain.Repair
import com.marcelm.informtechtask.errorhandling.ErrorEnums
import com.marcelm.informtechtask.errorhandling.Outcome
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class RepairService(
    private val readRepairPort: ReadRepairPort,
    private val writeRepairPort: WriteRepairPort,
) : RepairStartUseCase,
    RepairFinishUseCase,
    RepairFindAllUseCase,
    RepairFindByOwnerUseCase,
    SubmitRepairCreateUseCase,
    SubmitRepairDeleteUseCase,
    SubmitRepairUpdateUseCase,
    StartOrStopRepairUseCase {
    override fun saveRepair(repair: Repair): Outcome.WithResult<Repair, ErrorEnums.PersistenceItemErrors> {
        require(!readRepairPort.existsRepairByDescription(repair.description)) {
            return Outcome.WithResult.Error(
                ErrorEnums.PersistenceItemErrors.NameAlreadyExists,
                "Repair already exists: ${repair.description}",
            )
        }
        return Outcome.WithResult.Ok(writeRepairPort.saveNew(repair))
    }

    override fun deleteRepairById(id: Long) {
        writeRepairPort.deleteById(id)
    }

    override fun updateRepair(repair: Repair): Outcome.WithResult<Repair, ErrorEnums.PersistenceItemErrors> {
        val res = writeRepairPort.update(repair)

        if (res != null) {
            return Outcome.WithResult.Ok(res)
        } else {
            return Outcome.WithResult.Error(ErrorEnums.PersistenceItemErrors.ItemNotFound, "Repair not found")
        }
    }

    override fun findAllRepairs(): Collection<Repair> = readRepairPort.fetchAll()

    override fun findRepairsByOwner(ownerId: Long): Collection<Repair> = readRepairPort.fetchByOwner(ownerId)

    private fun findRepairById(id: Long): Optional<Repair> = readRepairPort.fetchById(id)

    override fun startRepair(repair: Repair) {
        writeRepairPort.startRepair(repair)
    }

    override fun finishRepair(
        repair: Repair,
        successful: Boolean,
    ) {
        writeRepairPort.finishRepair(repair, successful)
    }

    override fun startRepair(id: Long): Outcome.WithError<ErrorEnums.RepairErrors> {
        val repairOpt = findRepairById(id)
        if (repairOpt.isEmpty) {
            return Outcome.WithError.Error(ErrorEnums.RepairErrors.RepairNotFound, "Repair not found (id $id)")
        }
        val repair = repairOpt.get()
        val repOutcome = repair.start()
        if (repOutcome is Outcome.WithError.Error) {
            return repOutcome
        }

        writeRepairPort.update(repair)
        return Outcome.WithError.Ok()
    }

    override fun stopRepair(
        id: Long,
        successful: Boolean,
    ): Outcome.WithError<ErrorEnums.RepairErrors> {
        val repairOpt = findRepairById(id)
        if (repairOpt.isEmpty) {
            return Outcome.WithError.Error(ErrorEnums.RepairErrors.RepairNotFound, "Repair not found (id $id)")
        }
        val repair = repairOpt.get()
        val repOutcome = repair.stop(successful)
        if (repOutcome is Outcome.WithError.Error) {
            return repOutcome
        }
        writeRepairPort.update(repair)
        return Outcome.WithError.Ok()
    }
}
