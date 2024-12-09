package com.marcelm.informtechtask.application.usecase.repair

import com.marcelm.informtechtask.errorhandling.ErrorEnums
import com.marcelm.informtechtask.errorhandling.Outcome

interface StartOrStopRepairUseCase {
    fun startRepair(id: Long): Outcome.WithError<ErrorEnums.RepairErrors>

    fun stopRepair(
        id: Long,
        successful: Boolean,
    ): Outcome.WithError<ErrorEnums.RepairErrors>
}
