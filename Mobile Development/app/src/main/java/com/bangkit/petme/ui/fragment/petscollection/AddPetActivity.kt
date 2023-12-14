package com.bangkit.petme.ui.fragment.petscollection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.petme.MainActivity
import com.bangkit.petme.databinding.ActivityAddPetBinding

class AddPetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java).apply {
                putExtra("page", "PetCollection")
            })
        }
    }
}