package com.marcelm.informtechtask.infrastructure.repository

import com.marcelm.informtechtask.infrastructure.repository.data.RepairPersistenceData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface RepairRepository : JpaRepository<RepairPersistenceData, Long> {
    fun findByDescription(description: String): Optional<RepairPersistenceData>

    fun findByOwnerId(ownerId: Long): Collection<RepairPersistenceData>
}
