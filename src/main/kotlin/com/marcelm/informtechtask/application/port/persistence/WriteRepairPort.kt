package com.marcelm.informtechtask.application.port.persistence

import com.marcelm.informtechtask.domain.Repair

interface WriteRepairPort {
    fun saveNew(repair: Repair): Repair

    fun update(repair: Repair): Repair?

    fun deleteById(repairId: Long)

    fun startRepair(repair: Repair)

    fun finishRepair(
        repair: Repair,
        successful: Boolean,
    )
}
