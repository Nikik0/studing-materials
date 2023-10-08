package com.nikik0.studingmaterials.dtos

data class CurrentUserInfo(
    val id: Long,
    val username: String,
    val role: String,
    val enabled: Boolean
)
