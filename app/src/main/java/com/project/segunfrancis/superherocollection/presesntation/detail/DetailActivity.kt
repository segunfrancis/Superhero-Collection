package com.project.segunfrancis.superherocollection.presesntation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.project.segunfrancis.superherocollection.R
import com.project.segunfrancis.superherocollection.databinding.ActivityDetailBinding
import com.project.segunfrancis.superherocollection.framework.domain.CharacterEntity
import com.project.segunfrancis.superherocollection.presesntation.utils.AppConstants.INTENT_KEY
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailActivityViewModel by lazy {
        ViewModelProvider(this).get(DetailActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setSupportActionBar(binding.toolbar)

        val character = intent.getSerializableExtra(INTENT_KEY) as CharacterEntity
        viewModel.endProgress.value = character.powerstats.speed.toFloat()

        GlobalScope.launch {
            viewModel.getProgress()
        }
        populateData(character)
    }

    private fun populateData(character: CharacterEntity) {
        binding.superHeroImage.load(character.images.md) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
        binding.superHeroNameText.text = character.name
        binding.superHeroBioText.text = character.connections.groupAffiliation
        viewModel.progress.observe(this, Observer { progress ->
            binding.testProgress.progress = progress
        })
    }
}
