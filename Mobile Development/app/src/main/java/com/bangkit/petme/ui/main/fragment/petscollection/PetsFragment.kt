package com.bangkit.petme.ui.main.fragment.petscollection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.petme.api.Response.PetCollectionResponseItem
import com.bangkit.petme.databinding.FragmentPetsBinding
import com.bangkit.petme.model.PetCollection
import com.bangkit.petme.viewmodel.PetsCollectionViewModel
import com.bangkit.petme.viewmodel.ViewModelFactory

class PetsFragment : Fragment() {
    private lateinit var binding: FragmentPetsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPetsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val petCollectionViewModel = ViewModelProvider(requireActivity(), ViewModelFactory.getInstance(requireActivity().application)).get(
            PetsCollectionViewModel::class.java)

        binding.rvPetsCollection.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPetsCollection.setHasFixedSize(true)
        petCollectionViewModel.searchBar.observe(requireActivity()){
            binding.edSearch.setText(it)
        }

        binding.edSearch.addTextChangedListener{
            petCollectionViewModel.searchItem(binding.edSearch.text.toString())
//            petCollectionViewModel.seachBarText(binding.edSearch.text.toString())
        }
        petCollectionViewModel.petsCollectionDisplay.observe(requireActivity()){
            showRecyclerView(it)
        }
    }

    private fun showRecyclerView(listPetCollection: List<PetCollectionResponseItem>){
        binding.rvPetsCollection.adapter = PetCollectionAdapter(listPetCollection)
    }

}