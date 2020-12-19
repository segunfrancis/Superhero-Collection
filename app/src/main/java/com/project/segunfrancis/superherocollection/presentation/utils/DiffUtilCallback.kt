package com.project.segunfrancis.superherocollection.presentation.utils

import androidx.recyclerview.widget.DiffUtil
import com.project.segunfrancis.superherocollection.framework.domain.CharacterEntity

class DiffUtilCallback : DiffUtil.ItemCallback<CharacterEntity>() {
    override fun areItemsTheSame(oldItem: CharacterEntity, newItem: CharacterEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CharacterEntity,
        newItem: CharacterEntity
    ): Boolean {
        return oldItem == newItem
    }
}