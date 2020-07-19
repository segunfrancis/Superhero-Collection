package com.project.segunfrancis.core.domain

import com.google.gson.annotations.SerializedName

data class ImagesEntity(
    @SerializedName("lg")
    val lg: String,
    @SerializedName("md")
    val md: String,
    @SerializedName("sm")
    val sm: String,
    @SerializedName("xs")
    val xs: String
)