package com.example.perpustakaankitab.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Kitab(
    val id: String,
    val title: String,
    val pengarang: String,
    @SerialName("isi_kitab")
    val isiKitab: String,
    val ringkasan: String,
    val tema: String
)