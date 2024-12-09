package com.marcelm.informtechtask.application.port.entrypoint

import com.marcelm.informtechtask.infrastructure.controller.dtos.RequestChangeRepairDto
import org.springframework.http.ResponseEntity

interface ChangeRepairEndpointPort {
    fun saveRepair(changeRepairInfoDto: RequestChangeRepairDto): ResponseEntity<out Any>

    fun updateRepair(
        id: Long,
        changeRepairInfoDto: RequestChangeRepairDto,
    ): ResponseEntity<out Any>

    fun deleteRepair(id: Long)

    fun startRepair(id: Long): ResponseEntity<out Any>

    fun stopRepair(
        id: Long,
        success: Boolean,
    ): ResponseEntity<out Any>
}
