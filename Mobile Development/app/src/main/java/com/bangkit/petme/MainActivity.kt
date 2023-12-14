package com.bangkit.petme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.bangkit.petme.model.PetCollection
import com.bangkit.petme.ui.fragment.petscollection.AddPetActivity
import com.bangkit.petme.ui.fragment.profile.ProfileFragment
import com.bangkit.petme.ui.fragment.HomeFragment
import com.bangkit.petme.ui.fragment.NotificationFragment
import com.bangkit.petme.ui.fragment.petscollection.PetsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView
    lateinit var addButton : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavMenu)
        addButton = findViewById<FloatingActionButton>(R.id.addFab)

        bottomNav.background = null

        if(intent.getStringExtra("page") == "Profile"){
            loadFragment(ProfileFragment())
            bottomNav.selectedItemId = R.id.account
        }else if(intent.getStringExtra("page") == "PetCollection"){
            loadFragment(PetsFragment())
            bottomNav.selectedItemId = R.id.pets
        }else{
            loadFragment(HomeFragment())
            bottomNav.selectedItemId = R.id.home
        }


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


        // SHOW BADGES NOTIFICATIONS
        var badge = bottomNav.getOrCreateBadge(R.id.notification)
        badge.isVisible = true
        badge.number = 99
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container,fragment)
        transaction.commit()
    }
}