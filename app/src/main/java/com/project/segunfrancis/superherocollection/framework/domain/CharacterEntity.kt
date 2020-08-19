package com.project.segunfrancis.superherocollection.framework.domain

import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "character_table")
data class CharacterEntity(
    @Embedded
    @SerializedName("appearance")
    val appearance: AppearanceEntity,

    @Embedded
    @SerializedName("biography")
    val biography: BiographyEntity,

    @Embedded
    @SerializedName("connections")
    val connections: ConnectionsEntity,

    @SerializedName("id")
    val id: Int,

    @Embedded
    @SerializedName("images")
    val images: ImagesEntity,

    @SerializedName("name")
    val name: String,

    @Embedded
    @SerializedName("powerstats")
    val powerstats: PowerstatsEntity,

    @SerializedName("slug")
    val slug: String,

    @Embedded
    @SerializedName("work")
    val work: WorkEntity,

    var isFavorite: Boolean = false
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CharacterEntity

        if (appearance != other.appearance) return false
        if (biography != other.biography) return false
        if (connections != other.connections) return false
        if (id != other.id) return false
        if (images != other.images) return false
        if (name != other.name) return false
        if (powerstats != other.powerstats) return false
        if (slug != other.slug) return false
        if (work != other.work) return false
        if (isFavorite != other.isFavorite) return false

        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

}