package com.project.segunfrancis.superherocollection.presentation.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.project.segunfrancis.superherocollection.R
import com.project.segunfrancis.superherocollection.databinding.FavoriteFragmentBinding
import com.project.segunfrancis.superherocollection.framework.domain.CharacterEntity
import com.project.segunfrancis.superherocollection.presentation.detail.DetailActivity
import com.project.segunfrancis.superherocollection.presentation.main.MainActivityViewModel
import com.project.segunfrancis.superherocollection.presentation.utils.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment(), OnFavoriteRecyclerItemClick {

    private lateinit var binding: FavoriteFragmentBinding
    @Inject lateinit var viewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.favorite_fragment, container, false)
        binding = FavoriteFragmentBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = FavoriteRecyclerAdapter(this)
        binding.favSuperHeroRecyclerView.adapter = adapter
        binding.favSuperHeroRecyclerView.addItemDecoration(
            MarginItemDecoration(
                requireContext().resources.getInteger(R.integer.span_count),
                16,
                AppConstants.convertDpToPx(requireContext())
            )
        )
        viewModel.allFavorites.observe(viewLifecycleOwner, Observer {
            binding.emptyAnimation.isVisible = it.isNullOrEmpty()
            adapter.submitList(it)
        })
    }

    override fun onItemClick(characterEntity: CharacterEntity?) {
        startActivity(
            Intent(
                requireContext(),
                DetailActivity::class.java
            ).putExtra(AppConstants.INTENT_KEY, characterEntity)
        )
    }
}