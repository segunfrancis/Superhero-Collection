package com.project.segunfrancis.superherocollection.presesntation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.google.android.material.snackbar.Snackbar
import com.project.segunfrancis.superherocollection.Injection
import com.project.segunfrancis.superherocollection.R
import com.project.segunfrancis.superherocollection.databinding.FragmentHomeBinding
import com.project.segunfrancis.superherocollection.framework.domain.CharacterEntity
import com.project.segunfrancis.superherocollection.presesntation.detail.DetailActivity
import com.project.segunfrancis.superherocollection.presesntation.main.MainActivityViewModel
import com.project.segunfrancis.superherocollection.presesntation.main.SuperHeroLoadStateAdapter
import com.project.segunfrancis.superherocollection.presesntation.main.SuperHeroRecyclerAdapter
import com.project.segunfrancis.superherocollection.presesntation.utils.AppConstants.INTENT_KEY
import com.project.segunfrancis.superherocollection.presesntation.utils.AppConstants.convertDpToPx
import com.project.segunfrancis.superherocollection.presesntation.utils.computeScrollPosition
import com.project.segunfrancis.superherocollection.presesntation.utils.MarginItemDecoration
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */

class HomeFragment : Fragment(), SuperHeroRecyclerAdapter.OnRecyclerItemClick {

    private lateinit var binding: FragmentHomeBinding
    private var searchJob: Job? = null
    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(requireActivity(), Injection.provideViewModelFactory()).get(
            MainActivityViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Compute scroll position of RecyclerView
        binding.superHeroRecyclerView.computeScrollPosition(viewModel)

        val adapter = SuperHeroRecyclerAdapter(this)
        binding.superHeroRecyclerView.addItemDecoration(
            MarginItemDecoration(
                requireContext().resources.getInteger(R.integer.span_count),
                16,
                convertDpToPx(requireContext())
            )
        )
        binding.retryButton.setOnClickListener { adapter.retry() }
        binding.superHeroRecyclerView.adapter = adapter.withLoadStateFooter(
            footer = SuperHeroLoadStateAdapter { adapter.retry() }
        )
        adapter.addLoadStateListener { loadState ->
            binding.superHeroRecyclerView.isVisible =
                loadState.source.refresh is LoadState.NotLoading
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error

            // Display on any error, regardless of whether it came from RemoteMediator or PagingSource
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.source.refresh as? LoadState.Error
                ?: loadState.refresh as? LoadState.Error
            errorState?.let {
                displaySnackBar(it.error.localizedMessage!!)
            }
        }
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.superHeroesData.collect {
                adapter.submitData(it)
            }
        }
    }

    private fun displaySnackBar(message: String) {
        Snackbar.make(binding.homeConstraintLayout, message, Snackbar.LENGTH_LONG).show()
    }

    override fun onItemClick(characterEntity: CharacterEntity?) {
        startActivity(
            Intent(
                requireContext(),
                DetailActivity::class.java
            ).putExtra(INTENT_KEY, characterEntity)
        )
    }
}