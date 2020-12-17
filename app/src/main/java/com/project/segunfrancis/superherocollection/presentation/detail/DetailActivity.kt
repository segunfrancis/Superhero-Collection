package com.project.segunfrancis.superherocollection.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.project.segunfrancis.superherocollection.R
import com.project.segunfrancis.superherocollection.databinding.ActivityDetailBinding
import com.project.segunfrancis.superherocollection.framework.domain.CharacterEntity
import com.project.segunfrancis.superherocollection.presentation.utils.AppConstants.INTENT_KEY

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val character = intent.getSerializableExtra(INTENT_KEY) as CharacterEntity
        populateData(character)
    }

    private fun populateData(character: CharacterEntity) {
        binding.includeMotion.superHeroImage.load(character.images.md) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
        binding.includeMotion.name.text = character.name
        binding.include.combatProgressView.apply {
            progress = character.powerstats.combat.toFloat()
            labelText = character.powerstats.combat.toString().plus("%")
        }
        binding.include.powerProgressView.apply {
            progress = character.powerstats.power.toFloat()
            labelText = character.powerstats.power.toString().plus("%")
        }
        binding.include.intelligenceProgressView.apply {
            progress = character.powerstats.intelligence.toFloat()
            labelText = character.powerstats.intelligence.toString().plus("%")
        }
        binding.include.durabilityProgressView.apply {
            progress = character.powerstats.durability.toFloat()
            labelText = character.powerstats.durability.toString().plus("%")
        }
        binding.include.strengthProgressView.apply {
            progress = character.powerstats.strength.toFloat()
            labelText = character.powerstats.strength.toString().plus("%")
        }
        binding.include.speedProgressView.apply {
            progress = character.powerstats.speed.toFloat()
            labelText = character.powerstats.speed.toString().plus("%")
        }

        binding.include.fullNameTextView.text = character.biography.fullName
        binding.include.aliasesTextView.text = character.biography.aliases[0]
        binding.include.placeOfBirthTextView.text = character.biography.placeOfBirth
        binding.include.alignmentTextView.text = character.biography.alignment

        binding.include.occupationTextView.text = character.work.occupation
        binding.include.baseTextView.text = character.work.base

        binding.include.groupAffiliationTextView.text = character.connections.groupAffiliation
        binding.include.relativesTextView.text = character.connections.relatives

        binding.include.genderTextView.text = character.appearance.gender
        binding.include.eyeColorTextView.text = character.appearance.eyeColor
        binding.include.hairColorTextView.text = character.appearance.hairColor
        binding.include.raceTextView.text = character.appearance.race
        binding.include.heightTextView.text = character.appearance.height[1]
        binding.include.weightTextView.text = character.appearance.weight[1]
    }
}
