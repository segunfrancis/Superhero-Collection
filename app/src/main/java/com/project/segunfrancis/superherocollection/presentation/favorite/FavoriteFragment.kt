package com.project.segunfrancis.superherocollection.presentation.favorite

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.project.segunfrancis.superherocollection.Injection
import com.project.segunfrancis.superherocollection.R
import com.project.segunfrancis.superherocollection.databinding.FavoriteFragmentBinding
import com.project.segunfrancis.superherocollection.framework.domain.CharacterEntity
import com.project.segunfrancis.superherocollection.presentation.main.MainActivityViewModel
import com.project.segunfrancis.superherocollection.presentation.main.SuperHeroRecyclerAdapter
import com.project.segunfrancis.superherocollection.presentation.utils.AppConstants
import com.project.segunfrancis.superherocollection.presentation.utils.MarginItemDecoration
import com.project.segunfrancis.superherocollection.presentation.utils.OnRecyclerItemClick
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment(), OnRecyclerItemClick {

    private var job: Job? = null
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
        super.onViewCreated(view, savedInstanceState)

        val adapter = FavoriteRecyclerAdapter(this)
        binding.superHeroRecyclerView.adapter = adapter
        binding.superHeroRecyclerView.addItemDecoration(
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

    }

    override fun onItemLike(characterEntity: CharacterEntity) {

    }

    override fun onItemUnlike(characterEntity: CharacterEntity) {

    }
}