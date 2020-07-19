package com.project.segunfrancis.core.domain

import com.google.gson.annotations.SerializedName

data class WorkEntity(
    @SerializedName("base")
    val base: String,
    @SerializedName("occupation")
    val occupation: String
)