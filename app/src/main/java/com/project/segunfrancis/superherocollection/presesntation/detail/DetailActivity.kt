package com.project.segunfrancis.superherocollection.presesntation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.segunfrancis.superherocollection.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
    }
}
