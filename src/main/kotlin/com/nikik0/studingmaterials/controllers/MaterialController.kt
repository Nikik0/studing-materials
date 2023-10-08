package com.nikik0.studingmaterials.controllers

import com.nikik0.studingmaterials.entities.MaterialEntity
import com.nikik0.studingmaterials.services.MaterialService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/materials")
class MaterialController(
    private val materialService: MaterialService
) {

    @GetMapping("/get/{id}")
    suspend fun getSingle(@PathVariable id: Long, @RequestHeader("Authorization") auth: String) =
        materialService.getSingle(id, auth)

    @GetMapping("/get/all")
    suspend fun getAll(@RequestHeader("Authorization") auth: String) =
        materialService.getAll(auth)

    @PostMapping("/create")
    suspend fun create(@RequestBody materialEntity: MaterialEntity, @RequestHeader("Authorization") auth: String) =
        materialService.create(materialEntity, auth)

    @PostMapping("/delete/{id}")
    suspend fun delete(@PathVariable id: Long, @RequestHeader("Authorization") auth: String) =
        materialService.delete(id, auth)

    @PostMapping("/update")
    suspend fun update(@RequestBody materialEntity: MaterialEntity, @RequestHeader("Authorization") auth: String) =
        materialService.update(materialEntity, auth)
}