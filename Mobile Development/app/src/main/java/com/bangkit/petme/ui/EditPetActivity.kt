package com.bangkit.petme.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.petme.MainActivity
import com.bangkit.petme.R
import com.bangkit.petme.databinding.ActivityEditPetBinding
import com.bumptech.glide.Glide

class EditPetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditPetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditPetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.edTitle.setText(intent.getStringExtra(NAME))
        binding.edDescription.setText(intent.getStringExtra(DESCRIPTION))
        binding.edBreed.setText(intent.getStringExtra(TYPE))
        Glide.with(this)
            .load(intent.getStringExtra(IMAGE)) // URL Gambar
            .into(binding.ivImagePet)
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    companion object{
        val NAME: String = "name"
        val TYPE: String = "type"
        val IMAGE: String = "image"
        val DESCRIPTION: String = "description"
    }
}