package com.project.segunfrancis.superherocollection.presentation.utils

import com.project.segunfrancis.superherocollection.framework.domain.CharacterEntity

/**
 * Created by SegunFrancis
 */

interface OnRecyclerItemClick {
    fun onItemClick(characterEntity: CharacterEntity?)
    fun onItemLike(characterEntity: CharacterEntity)
    fun onItemUnlike(characterEntity: CharacterEntity)
}