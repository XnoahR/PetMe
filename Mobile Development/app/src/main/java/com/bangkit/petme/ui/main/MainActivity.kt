package com.bangkit.petme.ui.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bangkit.petme.R
import com.bangkit.petme.ui.main.fragment.petscollection.AddPetActivity
import com.bangkit.petme.ui.main.fragment.profile.ProfileFragment
import com.bangkit.petme.ui.main.fragment.home.HomeFragment
import com.bangkit.petme.ui.main.fragment.announcement.NotificationFragment
import com.bangkit.petme.ui.main.fragment.petscollection.PetsFragment
import com.bangkit.petme.viewmodel.MainViewModel
import com.bangkit.petme.viewmodel.PetsCollectionViewModel
import com.bangkit.petme.viewmodel.ViewModelFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView
    lateinit var addButton : FloatingActionButton
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var mainViewModel: MainViewModel
    private lateinit var petCollectionViewModel: PetsCollectionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mainViewModel =
            ViewModelProvider(this, ViewModelFactory.getInstance(this.application)).get(
                MainViewModel::class.java
            )

        petCollectionViewModel =
            ViewModelProvider(this, ViewModelFactory.getInstance(this.application)).get(
                PetsCollectionViewModel::class.java
            )
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavMenu)
        addButton = findViewById<FloatingActionButton>(R.id.addFab)

        bottomNav.background = null

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.pets -> {
                    loadFragment(PetsFragment())
                    true
                }
                R.id.notification -> {
                    loadFragment(NotificationFragment())
                    true
                }
                R.id.account -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
        addButton.setOnClickListener {
            intent = Intent(this, AddPetActivity::class.java)
            startActivity(intent)
        }
        if(intent.getStringExtra("page") != null){
            loadFragment(PetsFragment())
            bottomNav.selectedItemId = R.id.pets
            return
//            Log.d("ini cokkot", intent.getStringExtra("page")!!)
        }
        getLocation()
        loadFragment(HomeFragment())

        // SHOW BADGES NOTIFICATIONS
        var badge = bottomNav.getOrCreateBadge(R.id.notification)
        badge.isVisible = true
//        badge.number = 99
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }

    private fun getLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),100
            )
            return
        }

        val location = fusedLocationProviderClient.lastLocation
        location.addOnSuccessListener {
            if(it != null){
                mainViewModel.setLatitude(it.latitude.toFloat())
                mainViewModel.setLongitude(it.longitude.toFloat())
            }
        }
    }
}