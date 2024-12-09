package com.marcelm.informtechtask.application.usecase.repair

import com.marcelm.informtechtask.domain.Repair
import com.marcelm.informtechtask.errorhandling.ErrorEnums
import com.marcelm.informtechtask.errorhandling.Outcome

interface SubmitRepairUpdateUseCase {
    fun updateRepair(repair: Repair): Outcome.WithResult<Repair, ErrorEnums.PersistenceItemErrors>
}
