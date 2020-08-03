package com.project.segunfrancis.superherocollection.presesntation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.iterator
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.project.segunfrancis.superherocollection.databinding.ActivityMainBinding
import com.project.segunfrancis.superherocollection.Injection
import com.project.segunfrancis.superherocollection.R

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.homeFragment, R.id.favoriteFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNavView.setupWithNavController(navController)
        initDestinationListener(navController)

        viewModel = ViewModelProvider(
            this,
            Injection.provideViewModelFactory()
        )
            .get(MainActivityViewModel::class.java)

        observeScrollPosition()
    }

    private fun initDestinationListener(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            // Enables all option menus in the nav bar
            binding.bottomNavView.menu.iterator().forEach { menuItem ->
                menuItem.isEnabled = true
            }

            val menu = binding.bottomNavView.menu.findItem(destination.id)
            menu?.isEnabled = false
        }
    }

    private fun observeScrollPosition() {
        viewModel.scrollYPosition.observe(this, Observer { position ->
            if (position == 0) {
                binding.toolbar.elevation = 0f
            } else {
                // RecyclerView has left original position, elevate toolbar
                binding.toolbar.elevation = 4f
            }
            Log.d("MainActivityScroll", "Position: $position")
        })
    }
}
