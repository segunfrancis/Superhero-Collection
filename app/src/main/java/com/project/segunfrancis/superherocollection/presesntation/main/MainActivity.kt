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
        viewModel.superHeroes.observe(this, Observer {
            displaySnackBar(it.characters[1].name)
        })
        viewModel.error.observe(this, Observer { error ->
            displaySnackBar(error)
        })
    }

    private fun displaySnackBar(message: String) {
        Snackbar.make(main_constraintLayout, message, Snackbar.LENGTH_LONG).show()
    }
}
