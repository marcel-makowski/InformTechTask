package com.marcelm.informtechtask.application.usecase.repair

import com.marcelm.informtechtask.domain.Repair

interface RepairFindByOwnerUseCase {
    fun findRepairsByOwner(ownerId: Long): Collection<Repair>
}
