package com.marcelm.informtechtask.infrastructure.controller

import com.marcelm.informtechtask.application.port.entrypoint.ChangeDeviceEndpointPort
import com.marcelm.informtechtask.application.port.entrypoint.FindDeviceEndpointPort
import com.marcelm.informtechtask.application.port.entrypoint.FindRepairEndpointPort
import com.marcelm.informtechtask.infrastructure.controller.dtos.RequestChangeDeviceDto
import com.marcelm.informtechtask.infrastructure.controller.dtos.ResponseDeviceDto
import com.marcelm.informtechtask.infrastructure.controller.dtos.ResponseRepairDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["http://localhost:4200"], maxAge = 3600)
@RestController
@RequestMapping("/devices")
class DeviceController(
    private val findDeviceEndpointPort: FindDeviceEndpointPort,
    private val changeDeviceEndpointPort: ChangeDeviceEndpointPort,
    private val findRepairEndpointPort: FindRepairEndpointPort,
) {
    @RequestMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createDevice(
        @RequestBody device: RequestChangeDeviceDto,
    ): ResponseEntity<out Any> = changeDeviceEndpointPort.saveDevice(device)

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateDevice(
        @PathVariable(value = "id") id: Long,
        @RequestBody device: RequestChangeDeviceDto,
    ): ResponseDeviceDto = changeDeviceEndpointPort.updateDevice(id, device)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteDeviceById(
        @PathVariable(value = "id") id: Long,
    ) {
        changeDeviceEndpointPort.deleteDevice(id)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getDeviceById(
        @PathVariable(value = "id") id: Long,
    ): ResponseDeviceDto = findDeviceEndpointPort.fetchDeviceById(id)

    @GetMapping("/{id}/repairs")
    @ResponseStatus(HttpStatus.OK)
    fun getDeviceRepairs(
        @PathVariable(value = "id") id: Long,
    ): Collection<ResponseRepairDto> = findRepairEndpointPort.fetchRepairsForOwner(id)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getDevices(): Collection<ResponseDeviceDto> = findDeviceEndpointPort.fetchAllDevices()
}
