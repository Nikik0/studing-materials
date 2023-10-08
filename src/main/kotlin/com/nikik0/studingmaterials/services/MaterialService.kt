package com.nikik0.studingmaterials.services

import com.nikik0.studingmaterials.dtos.CurrentUserInfo
import com.nikik0.studingmaterials.entities.MaterialEntity
import com.nikik0.studingmaterials.repositories.MaterialRepository
import kotlinx.coroutines.flow.toList
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlow

@Service
class MaterialService(
    private val materialRepository: MaterialRepository
) {

    private val baseurl = "http://localhost:8080/api/v1/auth/info"

    private val securityProvider = WebClient.builder().baseUrl(baseurl).build()

    private suspend fun callToCheckCred(authentication: String, acceptedRoles: List<String>): Boolean {
        val currentUserInfo =
            securityProvider.get().header(HttpHeaders.AUTHORIZATION, authentication).retrieve()
                .bodyToFlow<CurrentUserInfo>()
                .toList().first()
        return acceptedRoles.contains(currentUserInfo.role.lowercase())
    }

    suspend fun create(materialEntity: MaterialEntity, auth: String) =
        if (callToCheckCred(auth, listOf("professor", "mentor", "admin")))
            materialRepository.save(materialEntity)
        else null

    suspend fun delete(id: Long, auth: String) =
        if (callToCheckCred(auth, listOf("professor", "admin")))
            materialRepository.deleteById(id)
        else null

    suspend fun getSingle(id: Long, auth: String) =
        if (callToCheckCred(auth, listOf("enrollee", "student","professor", "mentor", "admin")))
            materialRepository.findById(id)
        else null

    suspend fun getAll(auth: String) =
        if (callToCheckCred(auth, listOf("enrollee", "student","professor", "mentor", "admin")))
            materialRepository.findAll()
        else null

    suspend fun update(materialEntity: MaterialEntity, auth: String) =
        if (callToCheckCred(auth, listOf("professor", "mentor", "admin")))
            materialRepository.save(materialEntity)
        else null
}