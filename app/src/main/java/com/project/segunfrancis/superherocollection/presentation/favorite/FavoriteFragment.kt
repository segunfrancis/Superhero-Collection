package com.project.segunfrancis.superherocollection.presentation.favorite

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.project.segunfrancis.superherocollection.Injection
import com.project.segunfrancis.superherocollection.R
import com.project.segunfrancis.superherocollection.databinding.FavoriteFragmentBinding
import com.project.segunfrancis.superherocollection.framework.domain.CharacterEntity
import com.project.segunfrancis.superherocollection.presentation.detail.DetailActivity
import com.project.segunfrancis.superherocollection.presentation.main.MainActivityViewModel
import com.project.segunfrancis.superherocollection.presentation.utils.*

class FavoriteFragment : Fragment(), OnFavoriteRecyclerItemClick {

    private lateinit var binding: FavoriteFragmentBinding
    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(
            this,
            (requireActivity().application as Injection).viewModelFactory
        )[MainActivityViewModel::class.java]
    }

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