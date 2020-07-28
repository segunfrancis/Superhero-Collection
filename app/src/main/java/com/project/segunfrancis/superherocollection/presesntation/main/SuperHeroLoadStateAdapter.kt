package com.project.segunfrancis.superherocollection.presesntation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.segunfrancis.superherocollection.R
import com.project.segunfrancis.superherocollection.databinding.LoadStateFooterViewItemBinding

/**
 * Created by SegunFrancis
 */
class SuperHeroLoadStateAdapter(private val retry: () -> Unit): LoadStateAdapter<SuperHeroLoadStateAdapter.SuperHeroLoadStateViewHolder>() {

    /**
     * Called to bind the passed LoadState to the ViewHolder.
     *
     * @param loadState LoadState to display.
     *
     * @see [getItemViewType]
     * @see [displayLoadStateAsItem]
     */
    override fun onBindViewHolder(holder: SuperHeroLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    /**
     * Called to create a ViewHolder for the given LoadState.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param loadState The LoadState to be initially presented by the new ViewHolder.
     *
     * @see [getItemViewType]
     * @see [displayLoadStateAsItem]
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): SuperHeroLoadStateViewHolder {
        return SuperHeroLoadStateViewHolder.create(parent, retry)
    }

    class SuperHeroLoadStateViewHolder(
        private val binding: LoadStateFooterViewItemBinding,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.retryButton.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.errorMsg.text = loadState.error.localizedMessage
            }
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.retryButton.isVisible = loadState !is LoadState.Loading
            binding.errorMsg.isVisible = loadState !is LoadState.Loading
        }

        companion object {
            fun create(parent: ViewGroup, retry: () -> Unit): SuperHeroLoadStateViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.load_state_footer_view_item, parent, false)
                val binding = LoadStateFooterViewItemBinding.bind(view)
                return SuperHeroLoadStateViewHolder(binding, retry)
            }
        }
    }
}