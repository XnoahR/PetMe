package com.bangkit.petme.model

data class PetCollection(
    val id: Int,
    val name: String,
    val type: String,
    val image: String,
    val description: String,
    val range: Long
)