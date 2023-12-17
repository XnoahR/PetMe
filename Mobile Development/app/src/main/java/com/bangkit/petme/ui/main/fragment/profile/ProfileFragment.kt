package com.bangkit.petme.ui.main.fragment.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.petme.api.Response.PetCollectionResponseItem
import com.bangkit.petme.databinding.FragmentProfileBinding
import com.bangkit.petme.model.PetCollection
import com.bangkit.petme.ui.welcome.WelcomeActivity
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

        binding.btnLogout.setOnClickListener {
            profileViewModel.deleteToken()
            startActivity(Intent(requireContext(), WelcomeActivity::class.java))
        }

        binding.rvFavoritePets.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavoritePets.setHasFixedSize(true)

//        profileViewModel.petsCollection.observe(requireActivity()){
//            showRecyclerView(it)
//        }

        var id = ""
        var name = ""
        var phone = ""
        var password = ""
        var email = ""
        profileViewModel.userProfile.observe(requireActivity()){
            binding.tvName.text = it[0]?.name ?: ""
        }

        profileViewModel.userProfile.observe(requireActivity()){
                        name = it[0]?.name.toString()
            email = it[0]?.email.toString()
            phone = it[0]?.phone.toString()
            password = it[0]?.password.toString()
            id = it[0]?.id.toString()

            binding.btnEdit.setOnClickListener {
                startActivity(Intent(requireContext(), EditProfileActivity::class.java).apply {
                    putExtra(EditProfileActivity.ID, id)
                    putExtra(EditProfileActivity.NAME, name)
                    putExtra(EditProfileActivity.EMAIL, email)
                    putExtra(EditProfileActivity.PHONE, phone)
                    putExtra(EditProfileActivity.PASSWORD, password)
                })
            }
        }

    }

    private fun showRecyclerView(listPetCollection: List<PetCollectionResponseItem>){
//        binding.rvFavoritePets.adapter = FavoritePetsAdapter(listPetCollection)
    }
}