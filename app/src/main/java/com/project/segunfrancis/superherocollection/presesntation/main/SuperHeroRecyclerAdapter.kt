package com.project.segunfrancis.superherocollection.presesntation.main

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import coil.api.load
import com.project.segunfrancis.superherocollection.R
import com.project.segunfrancis.superherocollection.framework.domain.CharacterEntity
import kotlinx.android.synthetic.main.item_super_hero.view.*

/**
 * Created by SegunFrancis
 */

class SuperHeroRecyclerAdapter(private val onItemClick: OnRecyclerItemClick) :
    PagingDataAdapter<CharacterEntity, SuperHeroRecyclerAdapter.SuperHeroRecyclerViewHolder>(
        ITEM_CALLBACK
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroRecyclerViewHolder {
        return SuperHeroRecyclerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_super_hero, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SuperHeroRecyclerViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClick)

    class SuperHeroRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: CharacterEntity?, onClick: OnRecyclerItemClick) = with(itemView) {
            item_superHero_imageView.load(item?.images?.md) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
            item_superHero_name_textView.text = item?.name
            item_superHero_slug_textView.text = item?.connections?.groupAffiliation
            itemView.setOnClickListener { onClick.onItemClick(item) }
        }
    }

    interface OnRecyclerItemClick {
        fun onItemClick(characterEntity: CharacterEntity?)
    }

    companion object {

        private val ITEM_CALLBACK = object : DiffUtil.ItemCallback<CharacterEntity>() {
            override fun areItemsTheSame(
                oldItem: CharacterEntity,
                newItem: CharacterEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CharacterEntity,
                newItem: CharacterEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}