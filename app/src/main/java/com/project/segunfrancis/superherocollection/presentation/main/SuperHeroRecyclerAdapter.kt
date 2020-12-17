package com.project.segunfrancis.superherocollection.presentation.main

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.like.LikeButton
import com.like.OnLikeListener
import com.project.segunfrancis.superherocollection.R
import com.project.segunfrancis.superherocollection.databinding.ItemSuperHeroBinding
import com.project.segunfrancis.superherocollection.framework.domain.CharacterEntity
import com.project.segunfrancis.superherocollection.presentation.utils.loadImage

/**
 * Created by SegunFrancis
 */

class SuperHeroRecyclerAdapter(
    private val onItemClick: (item: CharacterEntity) -> Unit,
    private val onItemLike: (item: CharacterEntity) -> Unit,
    private val onItemUnlike: (item: CharacterEntity) -> Unit,
    private val button: (lb: LikeButton, item: CharacterEntity) -> Unit
) :
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
        holder.bind(getItem(position), onItemClick, onItemLike, onItemUnlike, button)

    class SuperHeroRecyclerViewHolder(private val binding: ItemSuperHeroBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: CharacterEntity?,
            onItemClick: (item: CharacterEntity) -> Unit,
            onItemLike: (item: CharacterEntity) -> Unit,
            onItemUnlike: (item: CharacterEntity) -> Unit,
            button: (lb: LikeButton, item: CharacterEntity) -> Unit
        ) {
            binding.itemSuperHeroImageView.loadImage(item?.images?.md)

            binding.itemSuperHeroNameTextView.text = item?.name
            binding.itemSuperHeroSlugTextView.text = item?.connections?.groupAffiliation
            binding.likeButton.setOnLikeListener(object : OnLikeListener {
                override fun liked(likeButton: LikeButton?) {
                    onItemLike(item!!)
                }

                override fun unLiked(likeButton: LikeButton?) {
                    onItemUnlike(item!!)
                }
            })
            binding.root.setOnClickListener { onItemClick(item!!) }
            button(binding.likeButton, item!!)
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