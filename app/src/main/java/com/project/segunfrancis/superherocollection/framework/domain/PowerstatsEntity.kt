package com.project.segunfrancis.superherocollection.framework.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PowerstatsEntity(
    @SerializedName("combat")
    val combat: Int,
    @SerializedName("durability")
    val durability: Int,
    @SerializedName("intelligence")
    val intelligence: Int,
    @SerializedName("power")
    val power: Int,
    @SerializedName("speed")
    val speed: Int,
    @SerializedName("strength")
    val strength: Int
): Serializable