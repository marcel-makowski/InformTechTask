package com.marcelm.informtechtask.application.usecase.repair

import com.marcelm.informtechtask.domain.Repair

interface RepairStartUseCase {
    fun startRepair(repair: Repair)
}
