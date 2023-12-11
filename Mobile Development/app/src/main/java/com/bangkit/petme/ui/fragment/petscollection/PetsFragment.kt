package com.bangkit.petme.ui.fragment.petscollection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.petme.R
import com.bangkit.petme.databinding.FragmentPetsBinding
import com.bangkit.petme.model.PetCollection

class PetsFragment : Fragment() {
    private var _binding: FragmentPetsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPetsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val petCollectionViewModel = ViewModelProvider(requireActivity(), ViewModelFactory.getInstance(requireActivity().application)).get(PetsCollectionViewModel::class.java)

        binding.rvPetsCollection.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPetsCollection.setHasFixedSize(true)

        petCollectionViewModel.petsCollection.observe(requireActivity()){
            showRecyclerView(it)
        }
    }

    private fun showRecyclerView(listPetCollection: List<PetCollection>){
        binding.rvPetsCollection.adapter = PetCollectionAdapter(listPetCollection)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}