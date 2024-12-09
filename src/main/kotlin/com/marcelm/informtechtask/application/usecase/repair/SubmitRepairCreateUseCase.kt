package com.marcelm.informtechtask.application.usecase.repair

import com.marcelm.informtechtask.domain.Repair
import com.marcelm.informtechtask.errorhandling.ErrorEnums
import com.marcelm.informtechtask.errorhandling.Outcome

interface SubmitRepairCreateUseCase {
    fun saveRepair(repair: Repair): Outcome.WithResult<Repair, ErrorEnums.PersistenceItemErrors>
}