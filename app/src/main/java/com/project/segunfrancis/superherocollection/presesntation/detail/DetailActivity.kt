package com.project.segunfrancis.superherocollection.presesntation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import coil.api.load
import com.project.segunfrancis.superherocollection.R
import com.project.segunfrancis.superherocollection.databinding.ActivityDetailBinding
import com.project.segunfrancis.superherocollection.framework.domain.CharacterEntity
import com.project.segunfrancis.superherocollection.presesntation.utils.AppConstants.INTENT_KEY
import com.project.segunfrancis.superherocollection.presesntation.utils.testFun
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            super.onBackPressed()
        }

        val character = intent.getSerializableExtra(INTENT_KEY) as CharacterEntity

        lifecycleScope.launch {
            binding.include.combatProgress.testFun(character.powerstats.combat.toFloat())
        }
        lifecycleScope.launch {
            binding.include.powerProgress.testFun(character.powerstats.power.toFloat())
        }
        lifecycleScope.launch {
            binding.include.strengthProgress.testFun(character.powerstats.strength.toFloat())
        }
        lifecycleScope.launch {
            binding.include.durabilityProgress.testFun(character.powerstats.durability.toFloat())
        }
        lifecycleScope.launch {
            binding.include.speedProgress.testFun(character.powerstats.speed.toFloat())
        }
        lifecycleScope.launch {
            binding.include.intelligenceProgress.testFun(character.powerstats.intelligence.toFloat())
        }
        populateData(character)
    }

    private fun populateData(character: CharacterEntity) {
        binding.superHeroImage.load(character.images.md) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
        binding.include.superHeroNameText.text = character.name
        binding.include.superHeroBioText.text = character.connections.groupAffiliation
    }
}
