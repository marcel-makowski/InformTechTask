package com.marcelm.informtechtask.adapter.persistence

import com.marcelm.informtechtask.application.port.persistence.ReadRepairPort
import com.marcelm.informtechtask.domain.Repair
import com.marcelm.informtechtask.infrastructure.repository.RepairRepository
import org.springframework.stereotype.Component
import java.util.Optional

@Component
class ReadRepairAdapter(
    private val repairRepository: RepairRepository,
    private val repairJpaMapper: RepairJpaMapper,
) : ReadRepairPort {
    override fun existsRepairByDescription(description: String): Boolean = repairRepository.findByDescription(description).isPresent

    override fun existsRepairById(id: Long): Boolean = repairRepository.findById(id).isPresent

    override fun fetchAll(): List<Repair> =
        repairRepository
            .findAll()
            .map { repairJpaMapper.toDomain(it) }

    override fun fetchByOwner(ownerId: Long): Collection<Repair> =
        repairRepository.findByOwnerId(ownerId).map { repairJpaMapper.toDomain(it) }

    override fun fetchById(ownerId: Long): Optional<Repair> = repairRepository.findById(ownerId).map { repairJpaMapper.toDomain(it) }
}
