package com.example.perpustakaankitab.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Profile (
    val id: String,
    val nama: String,
    val tema: String
)