package com.marcelm.informtechtask.application.port.entrypoint

import com.marcelm.informtechtask.infrastructure.controller.dtos.RequestChangeDeviceDto
import com.marcelm.informtechtask.infrastructure.controller.dtos.ResponseDeviceDto
import org.springframework.http.ResponseEntity

interface ChangeDeviceEndpointPort {
    fun saveDevice(changeDeviceInfoDto: RequestChangeDeviceDto): ResponseEntity<out Any>

    fun updateDevice(
        id: Long,
        changeDeviceInfoDto: RequestChangeDeviceDto,
    ): ResponseDeviceDto

    fun deleteDevice(id: Long)
}
