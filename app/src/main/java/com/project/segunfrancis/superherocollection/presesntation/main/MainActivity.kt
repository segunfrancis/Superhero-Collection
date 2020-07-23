package com.project.segunfrancis.superherocollection.presesntation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.project.segunfrancis.core.domain.CharacterEntity
import com.project.segunfrancis.superherocollection.databinding.ActivityMainBinding
import com.project.segunfrancis.superherocollection.presesntation.Injection
import com.project.segunfrancis.superherocollection.presesntation.detail.DetailActivity
import com.project.segunfrancis.superherocollection.presesntation.utils.AppConstants.INTENT_KEY
import com.project.segunfrancis.superherocollection.presesntation.utils.Resource

class MainActivity : AppCompatActivity(), SuperHeroRecyclerAdapter.OnRecyclerItemClick {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(
            this,
            Injection.provideViewModelFactory()
        )
            .get(MainActivityViewModel::class.java)

        val adapter = SuperHeroRecyclerAdapter(this)

        viewModel.superHeroes.observe(this, Observer { resource ->
            when (resource) {
                is Resource.Loading -> {
                    displaySnackBar(resource.message)
                }
                is Resource.Success -> {
                    displaySnackBar(resource.result.characters[2].name)
                    adapter.loadCharacters(resource.result.characters)
                    binding.superHeroRecyclerView.adapter = adapter
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

    override fun onItemClick(characterEntity: CharacterEntity) {
        startActivity(
            Intent(
                this@MainActivity,
                DetailActivity::class.java
            ).putExtra(INTENT_KEY, characterEntity)
        )
    }
}
