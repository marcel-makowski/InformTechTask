package com.marcelm.informtechtask.adapter.api

import com.marcelm.informtechtask.application.port.entrypoint.ChangeRepairEndpointPort
import com.marcelm.informtechtask.application.usecase.repair.StartOrStopRepairUseCase
import com.marcelm.informtechtask.application.usecase.repair.SubmitRepairCreateUseCase
import com.marcelm.informtechtask.application.usecase.repair.SubmitRepairDeleteUseCase
import com.marcelm.informtechtask.application.usecase.repair.SubmitRepairUpdateUseCase
import com.marcelm.informtechtask.errorhandling.Outcome
import com.marcelm.informtechtask.infrastructure.controller.dtos.RequestChangeRepairDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class ChangeRepairEndpointAdapter(
    private val submitRepairCreateUseCase: SubmitRepairCreateUseCase,
    private val submitRepairDeleteUseCase: SubmitRepairDeleteUseCase,
    private val submitRepairUpdateUseCase: SubmitRepairUpdateUseCase,
    private val startOrStopRepairUseCase: StartOrStopRepairUseCase,
    private val repairDtoMapper: RepairDtoMapper,
) : ChangeRepairEndpointPort {
    override fun saveRepair(changeRepairInfoDto: RequestChangeRepairDto): ResponseEntity<out Any> {
        val repair = repairDtoMapper.fromRequestDto(changeRepairInfoDto)
        val outcome = submitRepairCreateUseCase.saveRepair(repair)
        if (outcome is Outcome.WithResult.Error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(outcome.detail)
        }
        return ResponseEntity.ok(repairDtoMapper.toResponseDto(outcome.value()))
    }

    override fun updateRepair(
        id: Long,
        changeRepairInfoDto: RequestChangeRepairDto,
    ): ResponseEntity<out Any> {
        val repair = repairDtoMapper.fromRequestDtoWithId(id, changeRepairInfoDto)
        val outcome = submitRepairUpdateUseCase.updateRepair(repair)
        if (outcome is Outcome.WithResult.Error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(outcome.detail)
        }
        return ResponseEntity.ok(repairDtoMapper.toResponseDto(outcome.value()))
    }

    override fun deleteRepair(id: Long) {
        submitRepairDeleteUseCase.deleteRepairById(id)
    }

    override fun startRepair(id: Long): ResponseEntity<out Any> {
        val outcome = startOrStopRepairUseCase.startRepair(id)
        if (outcome is Outcome.WithError.Error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(outcome.detail)
        }
        return ResponseEntity.ok().build()
    }

    override fun stopRepair(
        id: Long,
        success: Boolean,
    ): ResponseEntity<out Any> {
        val outcome = startOrStopRepairUseCase.stopRepair(id, success)
        if (outcome is Outcome.WithError.Error) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(outcome.detail)
        }
        return ResponseEntity.ok().build()
    }
}
