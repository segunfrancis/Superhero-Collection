package com.project.segunfrancis.superherocollection.framework.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by SegunFrancis
 */
data class SuperHeroEntity(
    @SerializedName("characters")
    val characters: List<CharacterEntity>,
    @SerializedName("next_cursor")
    val nextCursor: Int,
    @SerializedName("previous_cursor")
    val previousCursor: Int
): Serializable