package com.bangkit.petme.ui.detail

import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bangkit.petme.R
import com.bangkit.petme.databinding.ActivityDetailPetBinding
import com.bangkit.petme.preferences.Preferences
import com.bangkit.petme.utils.UtilsRange
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
            var phone = it.data.user.phone
            var type = it.data.idAnimal.toString()
            if(type == "1"){
                type = "dog"
            }else{
                "cat"
            }
            var title = it.data.title
            binding.btnAdopt.setOnClickListener {
                sendWA("${phone}", "I would like to adopt the $type, that you posted with the title $title")
            }
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

    private fun sendSMS(phoneNumber: String, message: String) {
        try{
            SmsManager.getDefault().sendTextMessage(phoneNumber,null,message,null,null)
            Toast.makeText(this, "SMS Sended", Toast.LENGTH_SHORT).show()
        }catch (e: Exception){

        }
    }

    private fun sendWA(phoneNumber: String, message: String) {
        val formattedPhoneNumber = "+62" + phoneNumber.substring(1)
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://wa.me/$formattedPhoneNumber?text=$message")
        startActivity(intent)
    }

    companion object{
        val ID = "id"
    }
}