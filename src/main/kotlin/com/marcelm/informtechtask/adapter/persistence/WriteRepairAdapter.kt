package com.marcelm.informtechtask.adapter.persistence

import com.marcelm.informtechtask.application.port.persistence.WriteRepairPort
import com.marcelm.informtechtask.domain.Repair
import com.marcelm.informtechtask.infrastructure.repository.RepairRepository
import org.springframework.stereotype.Component

@Component
class WriteRepairAdapter(
    private val repairRepository: RepairRepository,
    private val repairJpaMapper: RepairJpaMapper,
    private val deviceJpaMapper: DeviceJpaMapper,
) : WriteRepairPort {
    override fun saveNew(repair: Repair): Repair {
        val repairData =
            repairJpaMapper.toJpaEntity(repair)
        val repairSaved = repairRepository.save(repairData)
        return repairJpaMapper.toDomain(repairSaved)
    }

    override fun update(repair: Repair): Repair? {
        val repairId = repair.id!!
        return repairRepository
            .findById(repairId)
            .map { repairJpaMapper.toJpaEntity(repair, it) }
            .map { repairRepository.save(it) }
            .map { repairJpaMapper.toDomain(it) }
            .orElse(null)
    }

    override fun deleteById(repairId: Long) = repairRepository.deleteById(repairId)

    override fun startRepair(repair: Repair) {
        repair.start()
        repairRepository.save(repairJpaMapper.toJpaEntity(repair))
    }

    override fun finishRepair(
        repair: Repair,
        successful: Boolean,
    ) {
        repair.stop(successful)
        repairRepository.save(repairJpaMapper.toJpaEntity(repair))
    }
}
