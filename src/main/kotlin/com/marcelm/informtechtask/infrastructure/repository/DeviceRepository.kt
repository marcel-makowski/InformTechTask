package com.marcelm.informtechtask.infrastructure.repository

import com.marcelm.informtechtask.infrastructure.repository.data.DevicePersistenceData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DeviceRepository : JpaRepository<DevicePersistenceData, Long> {
    fun findByName(name: String): Collection<DevicePersistenceData>
}
