package com.bangkit.petme.ui.welcome

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.motion.widget.Debug.getLocation
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.bangkit.petme.ui.login.LoginActivity
import com.bangkit.petme.databinding.ActivityWelcomeBinding
import com.bangkit.petme.ui.main.MainActivity
import com.bangkit.petme.utils.NetworkUtils
import com.bangkit.petme.viewmodel.LoginViewModel
import com.bangkit.petme.viewmodel.ViewModelFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWelcomeBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val networkUtils = NetworkUtils()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Memeriksa ketersediaan jaringan
        if (!networkUtils.isNetworkAvailable(this)) {
            // Meminta pengguna menyalakan data/Wi-Fi
            networkUtils.requestNetworkActivation(this)
        }
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        binding.btnGetStarted.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }


    }

}