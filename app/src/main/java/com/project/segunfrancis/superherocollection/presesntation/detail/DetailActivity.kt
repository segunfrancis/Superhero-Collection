package com.project.segunfrancis.superherocollection.presesntation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.api.load
import com.project.segunfrancis.superherocollection.R
import com.project.segunfrancis.superherocollection.databinding.ActivityDetailBinding
import com.project.segunfrancis.superherocollection.framework.domain.CharacterEntity
import com.project.segunfrancis.superherocollection.presesntation.utils.AppConstants.INTENT_KEY
import com.project.segunfrancis.superherocollection.presesntation.utils.ExtensionFunctions.testFun
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

        lifecycleScope.launch {
            binding.combatProgress.testFun(character.powerstats.combat.toFloat())
            binding.powerProgress.testFun(character.powerstats.power.toFloat())
            binding.durabilityProgress.testFun(character.powerstats.durability.toFloat())
            binding.speedProgress.testFun(character.powerstats.speed.toFloat())
            binding.intelligenceProgress.testFun(character.powerstats.intelligence.toFloat())
            binding.strengthProgress.testFun(character.powerstats.strength.toFloat())
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
    }
}
