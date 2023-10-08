package com.nikik0.studingmaterials.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("materials")
data class MaterialEntity(
    @Id
    val id: Long,
    val name: String,
    @Column("hex_file_id")
    val hexFileId: String,
    val text: String
)
