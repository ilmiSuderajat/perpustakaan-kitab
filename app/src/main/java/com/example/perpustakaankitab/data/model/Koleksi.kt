package com.example.perpustakaankitab.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Koleksi(
    val id: String,
    @SerialName("user_id")
    val userId: String,
    @SerialName("kitab_id")
    val kitabId: String,
    @SerialName("created_at")
    val createdAt: String
)