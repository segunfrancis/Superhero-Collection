package com.project.segunfrancis.core.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AppearanceEntity(
    @SerializedName("eyeColor")
    val eyeColor: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("hairColor")
    val hairColor: String,
    @SerializedName("height")
    val height: List<String>,
    @SerializedName("race")
    val race: String,
    @SerializedName("weight")
    val weight: List<String>
): Serializable