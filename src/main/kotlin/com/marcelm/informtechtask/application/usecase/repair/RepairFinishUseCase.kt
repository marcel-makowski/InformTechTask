package com.marcelm.informtechtask.application.usecase.repair

import com.marcelm.informtechtask.domain.Repair

interface RepairFinishUseCase {
    fun finishRepair(
        repair: Repair,
        successful: Boolean,
    )
}
