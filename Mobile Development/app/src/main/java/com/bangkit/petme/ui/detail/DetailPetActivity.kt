package com.bangkit.petme.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bangkit.petme.R
import com.bangkit.petme.databinding.ActivityDetailPetBinding
import com.bangkit.petme.preferences.Preferences
import com.bangkit.petme.utils.UtilsRange
import com.bangkit.petme.viewmodel.MainViewModel
import com.bangkit.petme.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide

class DetailPetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPetBinding
    private lateinit var detailViewModel: DetailPetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailViewModel =
            ViewModelProvider(this, ViewModelFactory.getInstance(this.application)).get(
                DetailPetViewModel::class.java
            )

        val preference: Preferences = Preferences(this)

        val id = intent.getIntExtra(ID, 0)

        detailViewModel.getDetailPostPet(id)

        detailViewModel.detailPostPet.observe(this){
            binding.tvTitle.text = it.data.title
            if(it.data.idAnimal == 1){
                binding.tvType.text = "Kucing"
            }else{
                binding.tvType.text = "Anjing"
            }
            Glide.with(this)
                .load(it.data.postPicture) // URL Gambar
                .into(binding.ivCover)
            binding.tvDescription.text = it.data.description
            binding.tvRange.text = "${UtilsRange.calculateHaversineDistance(preference.getLatitude().toString().toDouble(), preference.getLongitude().toString().toDouble(), it.data.latitude.toString().toDouble(), it.data.longitude.toString().toDouble()).toInt().toString()} KM"
        }

        detailViewModel.isFavorite.observe(this){
            if (it) {
                val drawable = ContextCompat.getDrawable(this, R.drawable.ic_favorite_yes)
                binding.ivIcon.setImageDrawable(drawable)
                binding.ivIcon.setOnClickListener {
                    detailViewModel.deleteFavorite(id)
                }
            } else {
                // Jika ingin mengganti dengan drawable lain saat tidak ada favorit
                val drawable = ContextCompat.getDrawable(this, R.drawable.ic_favorite_no)
                binding.ivIcon.setImageDrawable(drawable)
                binding.ivIcon.setOnClickListener{
                    detailViewModel.addFavorite(id)
                }
            }
        }
    }

    companion object{
        val ID = "id"
    }
}