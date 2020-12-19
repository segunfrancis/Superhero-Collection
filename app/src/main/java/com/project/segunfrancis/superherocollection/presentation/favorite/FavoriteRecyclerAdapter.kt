package com.project.segunfrancis.superherocollection.presentation.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.like.LikeButton
import com.like.OnLikeListener
import com.project.segunfrancis.superherocollection.R
import com.project.segunfrancis.superherocollection.databinding.ItemSuperHeroBinding
import com.project.segunfrancis.superherocollection.framework.domain.CharacterEntity
import com.project.segunfrancis.superherocollection.presentation.favorite.FavoriteRecyclerAdapter.FavoriteViewHolder
import com.project.segunfrancis.superherocollection.presentation.utils.DiffUtilCallback
import com.project.segunfrancis.superherocollection.presentation.utils.loadImage

/**
 * Created by SegunFrancis
 */

class FavoriteRecyclerAdapter(
    private val onItemClick: (item: CharacterEntity) -> Unit,
    private val onItemLike: (item: CharacterEntity) -> Unit,
    private val onItemUnlike: (item: CharacterEntity) -> Unit,
    private val button: (lb: LikeButton, item: CharacterEntity) -> Unit
) :
    ListAdapter<CharacterEntity, FavoriteViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_super_hero, parent, false)
        return FavoriteViewHolder(ItemSuperHeroBinding.bind(view))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick, onItemLike, onItemUnlike, button)
    }

    class FavoriteViewHolder(private val binding: ItemSuperHeroBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: CharacterEntity,
            onClick: (item: CharacterEntity) -> Unit,
            onItemLike: (item: CharacterEntity) -> Unit,
            onItemUnlike: (item: CharacterEntity) -> Unit,
            button: (lb: LikeButton, item: CharacterEntity) -> Unit
        ) {
            binding.itemSuperHeroImageView.loadImage(item.images.md)

            binding.itemSuperHeroNameTextView.text = item.name
            binding.itemSuperHeroSlugTextView.text = item.connections.groupAffiliation

            binding.root.setOnClickListener { onClick(item) }
            binding.likeButton.setOnLikeListener(object : OnLikeListener {
                override fun liked(likeButton: LikeButton?) {
                    onItemLike(item)
                }

                override fun unLiked(likeButton: LikeButton?) {
                    onItemUnlike(item)
                }
            })
            button(binding.likeButton, item)
        }
    }

}