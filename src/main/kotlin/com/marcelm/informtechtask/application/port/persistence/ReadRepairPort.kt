package com.marcelm.informtechtask.application.port.persistence

import com.marcelm.informtechtask.domain.Repair
import java.util.Optional

interface ReadRepairPort {
    fun existsRepairByDescription(description: String): Boolean

    fun existsRepairById(id: Long): Boolean

    fun fetchAll(): List<Repair>

    fun fetchByOwner(ownerId: Long): Collection<Repair>

    fun fetchById(ownerId: Long): Optional<Repair>
}
