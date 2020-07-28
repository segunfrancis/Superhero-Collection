package com.project.segunfrancis.superherocollection.presesntation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.google.android.material.snackbar.Snackbar
import com.project.segunfrancis.superherocollection.databinding.ActivityMainBinding
import com.project.segunfrancis.superherocollection.Injection
import com.project.segunfrancis.superherocollection.framework.domain.CharacterEntity
import com.project.segunfrancis.superherocollection.presesntation.detail.DetailActivity
import com.project.segunfrancis.superherocollection.presesntation.utils.AppConstants.INTENT_KEY
import com.project.segunfrancis.superherocollection.presesntation.utils.MarginItemDecoration
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), SuperHeroRecyclerAdapter.OnRecyclerItemClick {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            Injection.provideViewModelFactory()
        )
            .get(MainActivityViewModel::class.java)

        val adapter = SuperHeroRecyclerAdapter(this)
        binding.superHeroRecyclerView.addItemDecoration(MarginItemDecoration(16))
        binding.retryButton.setOnClickListener { adapter.retry() }
        binding.superHeroRecyclerView.adapter = adapter.withLoadStateFooter(
            footer = SuperHeroLoadStateAdapter { adapter.retry() }
        )
        adapter.addLoadStateListener { loadState ->
            binding.superHeroRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error

            // Display on any error, regardless of whether it came from RemoteMediator or PagingSource
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.append as? LoadState.Error
            errorState?.let {
                displaySnackBar(it.error.localizedMessage!!)
            }
        }
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.fetchSuperHeroes().collect {
                adapter.submitData(it)
            }
        }
    }

    private fun displaySnackBar(message: String) {
        Snackbar.make(binding.mainConstraintLayout, message, Snackbar.LENGTH_LONG).show()
    }

    override fun onItemClick(characterEntity: CharacterEntity?) {
        startActivity(
            Intent(
                this@MainActivity,
                DetailActivity::class.java
            ).putExtra(INTENT_KEY, characterEntity)
        )
    }
}
