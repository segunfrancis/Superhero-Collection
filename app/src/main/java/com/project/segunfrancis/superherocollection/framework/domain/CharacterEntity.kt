package com.project.segunfrancis.superherocollection.framework.domain

import com.google.gson.annotations.SerializedName
import com.project.segunfrancis.superherocollection.framework.domain.AppearanceEntity
import java.io.Serializable

data class CharacterEntity(
    @SerializedName("appearance")
    val appearance: AppearanceEntity,
    @SerializedName("biography")
    val biography: BiographyEntity,
    @SerializedName("connections")
    val connections: ConnectionsEntity,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: ImagesEntity,
    @SerializedName("name")
    val name: String,
    @SerializedName("powerstats")
    val powerstats: PowerstatsEntity,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("work")
    val work: WorkEntity
): Serializable