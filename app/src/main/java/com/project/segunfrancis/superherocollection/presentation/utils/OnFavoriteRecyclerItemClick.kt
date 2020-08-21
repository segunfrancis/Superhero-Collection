package com.project.segunfrancis.superherocollection.presentation.utils

import com.project.segunfrancis.superherocollection.framework.domain.CharacterEntity

/**
 * Created by SegunFrancis
 */
interface OnFavoriteRecyclerItemClick {
    fun onItemClick(characterEntity: CharacterEntity?)
}