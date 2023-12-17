package com.bangkit.petme.ui.main.fragment.home

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.petme.R
import com.bangkit.petme.api.Response.FavoritePetsResponseItem
import com.bangkit.petme.api.Response.PostPetResponseItem
import com.bangkit.petme.databinding.FragmentHomeBinding
import com.bangkit.petme.ui.main.fragment.profile.FavoritePetsAdapter
import com.bangkit.petme.viewmodel.MainViewModel
import com.bangkit.petme.viewmodel.ViewModelFactory
import java.io.IOException
import java.util.Locale

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory.getInstance(requireActivity().application)
        ).get(
            MainViewModel::class.java
        )
        setLocation()
        binding.rvPostPet.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPostPet.setHasFixedSize(true)
        binding.edSearch.addTextChangedListener {
            mainViewModel.searchItem(binding.edSearch.text.toString())
        }

        mainViewModel.postPetDisplay.observe(requireActivity()) {
            showRecyclerView(it)
        }

        mainViewModel.isLoading.observe(requireActivity()) {
            if (it == true) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.INVISIBLE
            }
        }

        binding.btnAllPet.setOnClickListener {
            mainViewModel.setStateType("All")
            mainViewModel.searchItem(binding.edSearch.text.toString())
        }

        binding.btnCat.setOnClickListener {
            mainViewModel.setStateType("Cat")
            mainViewModel.searchItem(binding.edSearch.text.toString())
        }

        binding.btnDog.setOnClickListener {
            mainViewModel.setStateType("Dog")
            mainViewModel.searchItem(binding.edSearch.text.toString())
        }

        mainViewModel.isEmpty.observe(requireActivity()){
            if(it == true){
                binding.tvNoItem.visibility = View.VISIBLE
            }else{
                binding.tvNoItem.visibility = View.INVISIBLE
            }
        }

        mainViewModel.stateType.observe(requireActivity()) {
            Log.d("STATENYA", it)
            if (it == "All") {
                binding.btnAllPet.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#9188E3")));
                binding.btnCat.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#D7D2FF")));
                binding.btnDog.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#D7D2FF")));
            } else if (it == "Cat") {
                binding.btnAllPet.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#D7D2FF")));
                binding.btnCat.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#9188E3")));
                binding.btnDog.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#D7D2FF")));
            } else {
                binding.btnAllPet.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#D7D2FF")));
                binding.btnCat.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#D7D2FF")));
                binding.btnDog.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#9188E3")));
            }
        }

        mainViewModel.getPostPet()
    }


    private fun setLocation() {
        val geocoder = Geocoder(requireContext(), Locale.getDefault())
        var city = "Search for your location"
        var country = ""

        try {
            val addresses: MutableList<Address> = geocoder.getFromLocation(
                mainViewModel.getLatitude().toDouble(),
                mainViewModel.getLongitude().toDouble(),
                1
            )!!

            if (addresses.isNotEmpty()) {
                city = addresses[0].subAdminArea ?: ""
                country = addresses[0].countryName ?: ""
            }
        } catch (e: IOException) {
            Log.e("GeocodingTask", "Error retrieving geocoding data", e)
        }

        binding.tvLocation.setText("$city, $country")
    }

    private fun showRecyclerView(listPostPet: List<PostPetResponseItem>) {
        binding.rvPostPet.adapter = PostPetAdapter(listPostPet)
    }

    override fun onDestroy() {
        mainViewModel.resetList()
        super.onDestroy()
    }
}