package com.marcelm.informtechtask.application.port.entrypoint

import com.marcelm.informtechtask.infrastructure.controller.dtos.ResponseDeviceDto

interface FindDeviceEndpointPort {
    fun fetchAllDevices(): Collection<ResponseDeviceDto>

    fun fetchDeviceById(id: Long): ResponseDeviceDto
}
