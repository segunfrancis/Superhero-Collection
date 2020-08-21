package com.project.segunfrancis.superherocollection.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.iterator
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.badge.BadgeDrawable
import com.project.segunfrancis.superherocollection.databinding.ActivityMainBinding
import com.project.segunfrancis.superherocollection.Injection
import com.project.segunfrancis.superherocollection.R
import com.project.segunfrancis.superherocollection.presentation.settings.SettingsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(
            this,
            (this.application as Injection).viewModelFactory
        )[MainActivityViewModel::class.java]
    }
    private val badge: BadgeDrawable by lazy {
        binding.bottomNavView.getOrCreateBadge(R.id.favoriteFragment)
    }

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

        viewModel.showBadge.observe(this, Observer {
            if (it) addBadge()
            else hideBadge()
        })
    }

    private fun initDestinationListener(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            // Enables all option menus in the nav bar
            binding.bottomNavView.menu.iterator().forEach { menuItem ->
                menuItem.isEnabled = true
            }
            if (destination.id == R.id.favoriteFragment) viewModel.setShowBadge(false)
            val menu = binding.bottomNavView.menu.findItem(destination.id)
            menu?.isEnabled = false
        }
    }

    private fun addBadge() {
        badge.verticalOffset = 3
        badge.horizontalOffset = 3
        badge.backgroundColor = resources.getColor(R.color.power)
        badge.isVisible = true
    }

    private fun hideBadge() {
        badge.isVisible = false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_settings) {
            startActivity(Intent(this, SettingsActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
