package com.project.segunfrancis.superherocollection.presesntation.main

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.api.load
import com.project.segunfrancis.core.domain.CharacterEntity
import com.project.segunfrancis.superherocollection.R
import kotlinx.android.synthetic.main.item_super_hero.view.*
import java.util.*

/**
 * Created by SegunFrancis
 */
class SuperHeroRecyclerAdapter(private val onItemClick: OnRecyclerItemClick) :
    RecyclerView.Adapter<SuperHeroRecyclerAdapter.SuperHeroRecyclerViewHolder>() {

    private var characters: List<CharacterEntity> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroRecyclerViewHolder {
        return SuperHeroRecyclerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_super_hero, parent, false)
        )
    }

    fun loadCharacters(characters: List<CharacterEntity>) {
        this.characters = characters
    }

    override fun getItemCount() = characters.size

    override fun onBindViewHolder(holder: SuperHeroRecyclerViewHolder, position: Int) =
        holder.bind(characters[position], onItemClick)

    class SuperHeroRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: CharacterEntity, onClick: OnRecyclerItemClick) = with(itemView) {
            item_superHero_imageView.load(item.images.md) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
            item_superHero_name_textView.text = item.name
            item_superHero_slug_textView.text = item.connections.groupAffiliation
            itemView.setOnClickListener { onClick.onItemClick(item) }
        }
    }

    interface OnRecyclerItemClick {
        fun onItemClick(characterEntity: CharacterEntity)
    }
}