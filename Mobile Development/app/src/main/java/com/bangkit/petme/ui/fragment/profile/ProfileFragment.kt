package com.bangkit.petme.ui.fragment.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.petme.databinding.FragmentProfileBinding
import com.bangkit.petme.model.PetCollection
import com.bangkit.petme.viewmodel.ProfileViewModel
import com.bangkit.petme.viewmodel.ViewModelFactory

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val profileViewModel = ViewModelProvider(requireActivity(), ViewModelFactory.getInstance(requireActivity().application)).get(
            ProfileViewModel::class.java)


        binding.rvFavoritePets.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavoritePets.setHasFixedSize(true)

        profileViewModel.petsCollection.observe(requireActivity()){
            showRecyclerView(it)
        }

        binding.btnEdit.setOnClickListener {
            startActivity(Intent(requireContext(), EditProfileActivity::class.java))
        }
    }

    private fun showRecyclerView(listPetCollection: List<PetCollection>){
        binding.rvFavoritePets.adapter = FavoritePetsAdapter(listPetCollection)
    }
}