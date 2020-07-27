package com.project.segunfrancis.superherocollection.framework.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ConnectionsEntity(
    @SerializedName("groupAffiliation")
    val groupAffiliation: String,
    @SerializedName("relatives")
    val relatives: String
): Serializable