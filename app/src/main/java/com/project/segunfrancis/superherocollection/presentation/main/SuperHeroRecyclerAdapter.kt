package com.project.segunfrancis.superherocollection.presentation.main

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import coil.api.load
import com.like.LikeButton
import com.like.OnLikeListener
import com.project.segunfrancis.superherocollection.R
import com.project.segunfrancis.superherocollection.databinding.ItemSuperHeroBinding
import com.project.segunfrancis.superherocollection.framework.domain.CharacterEntity
import com.project.segunfrancis.superherocollection.presentation.utils.OnRecyclerItemClick

/**
 * Created by SegunFrancis
 */

class SuperHeroRecyclerAdapter(private val onItemClick: OnRecyclerItemClick) :
    PagingDataAdapter<CharacterEntity, SuperHeroRecyclerAdapter.SuperHeroRecyclerViewHolder>(
        ITEM_CALLBACK
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_super_hero, parent, false)
        return SuperHeroRecyclerViewHolder(
            ItemSuperHeroBinding.bind(view)
        )
    }

    override fun onBindViewHolder(holder: SuperHeroRecyclerViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClick)

    class SuperHeroRecyclerViewHolder(private val binding: ItemSuperHeroBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CharacterEntity?, onClick: OnRecyclerItemClick) {
            binding.itemSuperHeroImageView.load(item?.images?.md) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
            binding.itemSuperHeroNameTextView.text = item?.name
            binding.itemSuperHeroSlugTextView.text = item?.connections?.groupAffiliation
            binding.likeButton.setOnLikeListener(object : OnLikeListener {
                override fun liked(likeButton: LikeButton?) {
                    onClick.onItemLike(item!!)
                }

                override fun unLiked(likeButton: LikeButton?) {
                    onClick.onItemUnlike(item!!)
                }
            })
            binding.root.setOnClickListener { onClick.onItemClick(item) }
            binding.likeButton.isLiked = item!!.isFavorite
        }
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