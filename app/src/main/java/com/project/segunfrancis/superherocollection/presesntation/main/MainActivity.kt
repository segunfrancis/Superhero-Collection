package com.project.segunfrancis.superherocollection.presesntation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.project.segunfrancis.superherocollection.databinding.ActivityMainBinding
import com.project.segunfrancis.superherocollection.Injection
import com.project.segunfrancis.superherocollection.framework.domain.CharacterEntity
import com.project.segunfrancis.superherocollection.presesntation.detail.DetailActivity
import com.project.segunfrancis.superherocollection.presesntation.utils.AppConstants.INTENT_KEY
import com.project.segunfrancis.superherocollection.presesntation.utils.MarginItemDecoration
import com.project.segunfrancis.superherocollection.presesntation.utils.Resource
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
        binding.superHeroRecyclerView.adapter = adapter.withLoadStateFooter(
            footer = SuperHeroLoadStateAdapter { adapter.retry() }
        )
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.fetchSuperHeroes().collect {
                adapter.submitData(it)
            }
        }

        viewModel.superHeroes.observe(this, Observer { resource ->
            when (resource) {
                is Resource.Loading -> {
                    displaySnackBar(resource.message)
                }
                is Resource.Success -> {

                }
                is Resource.Error -> {
                    displaySnackBar(resource.message)
                }
            }
        })
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
