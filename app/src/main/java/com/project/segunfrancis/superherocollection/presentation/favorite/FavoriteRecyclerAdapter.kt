package com.project.segunfrancis.superherocollection.presentation.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.project.segunfrancis.superherocollection.R
import com.project.segunfrancis.superherocollection.databinding.ItemSuperHeroBinding
import com.project.segunfrancis.superherocollection.framework.domain.CharacterEntity
import com.project.segunfrancis.superherocollection.presentation.utils.OnRecyclerItemClick
import com.project.segunfrancis.superherocollection.presentation.favorite.FavoriteRecyclerAdapter.FavoriteViewHolder

/**
 * Created by SegunFrancis
 */

class FavoriteRecyclerAdapter(private val onItemClick: OnRecyclerItemClick) :
    ListAdapter<CharacterEntity, FavoriteViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_super_hero, parent, false)
        return FavoriteViewHolder(ItemSuperHeroBinding.bind(view))
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }

    class FavoriteViewHolder(private val binding: ItemSuperHeroBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CharacterEntity?, onClick: OnRecyclerItemClick) {
            binding.itemSuperHeroImageView.load(item?.images?.md) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
            binding.itemSuperHeroNameTextView.text = item?.name
            binding.itemSuperHeroSlugTextView.text = item?.connections?.groupAffiliation

            binding.root.setOnClickListener { onClick.onItemClick(item) }
            binding.likeButton.visibility = View.GONE
        }
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<CharacterEntity>() {
        override fun areItemsTheSame(oldItem: CharacterEntity, newItem: CharacterEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterEntity, newItem: CharacterEntity): Boolean {
            return oldItem.equals(newItem)
        }
    }
}