package com.bangkit.petme.ui.main.fragment

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.Debug.getLocation
import androidx.lifecycle.ViewModelProvider
import com.bangkit.petme.R
import com.bangkit.petme.databinding.FragmentHomeBinding
import com.bangkit.petme.databinding.FragmentProfileBinding
import com.bangkit.petme.viewmodel.MainViewModel
import com.bangkit.petme.viewmodel.ProfileViewModel
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
        mainViewModel = ViewModelProvider(requireActivity(), ViewModelFactory.getInstance(requireActivity().application)).get(
            MainViewModel::class.java)
        setLocation()
    }
    private fun setLocation(){
        val geocoder = Geocoder(requireContext(), Locale.getDefault())
        var city = "Search for your location"
        var country = ""

        try {
            val addresses: MutableList<Address> = geocoder.getFromLocation(mainViewModel.getLatitude().toDouble(), mainViewModel.getLongitude().toDouble(), 1)!!

            if (addresses.isNotEmpty()) {
                city = addresses[0].subAdminArea ?: ""
                country = addresses[0].countryName ?: ""
            }
        } catch (e: IOException) {
            Log.e("GeocodingTask", "Error retrieving geocoding data", e)
        }

        binding.tvLocation.setText("$city, $country")
    }
}