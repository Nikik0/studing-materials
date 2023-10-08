package com.nikik0.studingmaterials.repositories

import com.nikik0.studingmaterials.entities.MaterialEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MaterialRepository: CoroutineCrudRepository<MaterialEntity, Long> {
}