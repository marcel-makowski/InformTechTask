package com.marcelm.informtechtask.application.usecase.repair

import com.marcelm.informtechtask.domain.Repair

interface RepairFindAllUseCase {
    fun findAllRepairs(): Collection<Repair>
}
