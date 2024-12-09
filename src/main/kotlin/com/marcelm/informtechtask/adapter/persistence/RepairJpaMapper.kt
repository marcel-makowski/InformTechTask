package com.marcelm.informtechtask.adapter.persistence

import com.marcelm.informtechtask.domain.Repair
import com.marcelm.informtechtask.infrastructure.repository.data.RepairPersistenceData
import org.springframework.stereotype.Component

@Component
class RepairJpaMapper(
    val deviceMapper: DeviceJpaMapper,
) {
    fun toJpaEntity(repair: Repair) =
        RepairPersistenceData(
            repair.id,
            repair.ownerId,
            repair.description,
            repair.cost,
            repair.lastStartTime,
            repair.timeElapsedSeconds,
            repair.isSuccessful,
            repair.isInProgress,
        )

    fun toJpaEntity(
        repair: Repair,
        persistedRepairData: RepairPersistenceData,
    ) = persistedRepairData.copy(
        repairId = repair.id,
        ownerId = repair.ownerId,
        description = repair.description,
        cost = repair.cost,
        lastStartTime = repair.lastStartTime,
        timeElapsedSeconds = repair.timeElapsedSeconds,
        successful = repair.isSuccessful,
        inProgress = repair.isInProgress,
    )

    fun toDomain(repairData: RepairPersistenceData) =
        Repair(
            repairData.repairId,
            repairData.ownerId,
            repairData.description,
            repairData.cost,
            repairData.lastStartTime,
            repairData.timeElapsedSeconds,
            repairData.successful,
            repairData.inProgress,
        )
}
