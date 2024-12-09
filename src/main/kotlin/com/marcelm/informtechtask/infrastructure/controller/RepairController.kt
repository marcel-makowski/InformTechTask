package com.marcelm.informtechtask.infrastructure.controller

import com.marcelm.informtechtask.application.port.entrypoint.ChangeRepairEndpointPort
import com.marcelm.informtechtask.application.port.entrypoint.FindRepairEndpointPort
import com.marcelm.informtechtask.infrastructure.controller.dtos.RequestChangeRepairDto
import com.marcelm.informtechtask.infrastructure.controller.dtos.ResponseRepairDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["http://localhost:4200"], maxAge = 3600)
@RestController
@RequestMapping("/repairs")
class RepairController(
    private val changeRepairEndpointPort: ChangeRepairEndpointPort,
    private val findRepairEndpointPort: FindRepairEndpointPort,
) {
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createRepair(
        @RequestBody repair: RequestChangeRepairDto,
    ): ResponseEntity<out Any> = changeRepairEndpointPort.saveRepair(repair)

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateRepair(
        @PathVariable(value = "id") id: Long,
        @RequestBody repair: RequestChangeRepairDto,
    ): ResponseEntity<out Any> = changeRepairEndpointPort.updateRepair(id, repair)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteRepairById(
        @PathVariable(value = "id") id: Long,
    ) {
        changeRepairEndpointPort.deleteRepair(id)
    }

    @PostMapping("/{id}/start")
    @ResponseStatus(HttpStatus.OK)
    fun startRepair(
        @PathVariable(value = "id") id: Long,
    ): ResponseEntity<out Any> = changeRepairEndpointPort.startRepair(id)

    @PostMapping("/{id}/pause")
    @ResponseStatus(HttpStatus.OK)
    fun pauseRepair(
        @PathVariable(value = "id") id: Long,
    ): ResponseEntity<out Any> = changeRepairEndpointPort.stopRepair(id, false)

    @PostMapping("/{id}/end")
    @ResponseStatus(HttpStatus.OK)
    fun stopRepair(
        @PathVariable(value = "id") id: Long,
    ): ResponseEntity<out Any> = changeRepairEndpointPort.stopRepair(id, true)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getRepairs(): Collection<ResponseRepairDto> = findRepairEndpointPort.fetchAllRepairs()
}
