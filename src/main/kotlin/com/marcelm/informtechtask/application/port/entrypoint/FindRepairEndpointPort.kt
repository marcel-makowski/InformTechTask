package com.marcelm.informtechtask.application.port.entrypoint

import com.marcelm.informtechtask.infrastructure.controller.dtos.ResponseRepairDto

interface FindRepairEndpointPort {
    fun fetchAllRepairs(): Collection<ResponseRepairDto>

    fun fetchRepairsForOwner(ownerId: Long): Collection<ResponseRepairDto>
}
