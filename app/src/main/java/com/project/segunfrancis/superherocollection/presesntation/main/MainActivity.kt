package com.project.segunfrancis.superherocollection.presesntation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.project.segunfrancis.superherocollection.R
import com.project.segunfrancis.superherocollection.framework.remote.ApiHelper
import com.project.segunfrancis.superherocollection.framework.remote.ApiServiceImpl
import com.project.segunfrancis.superherocollection.presesntation.SuperHeroViewModelFactory
import com.project.segunfrancis.superherocollection.presesntation.utils.Resource
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(
            this,
            SuperHeroViewModelFactory(ApiHelper(ApiServiceImpl()))
        )
            .get(MainActivityViewModel::class.java)
        viewModel.progress.observe(this, Observer { progress ->
            test_progress.progress = progress
            test_progress.text = progress.toString()
        })
        viewModel.superHeroes.observe(this, Observer { resource ->
            when (resource) {
                is Resource.Loading -> {
                    displaySnackBar(resource.message)
                }
                is Resource.Success -> {
                    displaySnackBar(resource.result.characters[2].name)
                }
                is Resource.Error -> {
                    displaySnackBar(resource.message)
                }
            }
        })
    }

    private fun displaySnackBar(message: String) {
        Snackbar.make(main_constraintLayout, message, Snackbar.LENGTH_LONG).show()
    }
}
