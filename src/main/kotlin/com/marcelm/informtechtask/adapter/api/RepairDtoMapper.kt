package com.marcelm.informtechtask.adapter.api

import com.marcelm.informtechtask.domain.Repair
import com.marcelm.informtechtask.infrastructure.controller.dtos.RequestChangeRepairDto
import com.marcelm.informtechtask.infrastructure.controller.dtos.ResponseRepairDto
import org.springframework.stereotype.Component

@Component
class RepairDtoMapper(
    private val deviceDtoMapper: DeviceDtoMapper,
) {
    fun toResponseDto(repair: Repair): ResponseRepairDto =
        ResponseRepairDto
            .Builder(
                id = repair.id,
                ownerId = repair.ownerId,
                description = repair.description,
                cost = repair.cost,
                timeElapsedSeconds = repair.timeElapsedSeconds,
                isSuccessful = repair.isSuccessful,
                isInProgress = repair.isInProgress,
                lastStartTime = repair.lastStartTime,
            ).build()

    fun fromRequestDto(dto: RequestChangeRepairDto): Repair = fromRequestDtoWithId(null, dto)

    fun fromRequestDtoWithId(
        id: Long?,
        dto: RequestChangeRepairDto,
    ): Repair =
        Repair(
            id = dto.id,
            ownerId = dto.ownerId,
            description = dto.description,
            cost = dto.cost,
            timeElapsedSeconds = dto.timeElapsedSeconds,
            isSuccessful = dto.isSuccessful,
            isInProgress = dto.isInProgress,
            lastStartTime = dto.lastStartTime,
        )
}
