package com.project.segunfrancis.core.domain

import com.google.gson.annotations.SerializedName

data class BiographyEntity(
    @SerializedName("aliases")
    val aliases: List<String>,
    @SerializedName("alignment")
    val alignment: String,
    @SerializedName("alterEgos")
    val alterEgos: String,
    @SerializedName("firstAppearance")
    val firstAppearance: String,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("placeOfBirth")
    val placeOfBirth: String,
    @SerializedName("publisher")
    val publisher: String
)