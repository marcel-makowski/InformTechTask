package com.marcelm.informtechtask.adapter.api

import com.marcelm.informtechtask.application.port.entrypoint.FindRepairEndpointPort
import com.marcelm.informtechtask.application.usecase.repair.RepairFindAllUseCase
import com.marcelm.informtechtask.application.usecase.repair.RepairFindByOwnerUseCase
import com.marcelm.informtechtask.infrastructure.controller.dtos.ResponseRepairDto
import org.springframework.stereotype.Component

@Component
class FindRepairEndpointAdapter(
    private val repairFindAllUseCase: RepairFindAllUseCase,
    private val repairFindByOwnerUseCase: RepairFindByOwnerUseCase,
    private val repairDtoMapper: RepairDtoMapper,
) : FindRepairEndpointPort {
    override fun fetchAllRepairs(): Collection<ResponseRepairDto> =
        repairFindAllUseCase.findAllRepairs().map {
            repairDtoMapper.toResponseDto(it)
        }

    override fun fetchRepairsForOwner(ownerId: Long): Collection<ResponseRepairDto> =
        repairFindByOwnerUseCase.findRepairsByOwner(ownerId).map {
            repairDtoMapper.toResponseDto(it)
        }
}
