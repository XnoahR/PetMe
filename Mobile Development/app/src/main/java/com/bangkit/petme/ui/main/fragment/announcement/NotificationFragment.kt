package com.bangkit.petme.ui.main.fragment.announcement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.petme.api.Response.Data
import com.bangkit.petme.api.Response.DataItem
import com.bangkit.petme.api.Response.PetCollectionResponseItem
import com.bangkit.petme.databinding.FragmentNotificationBinding
import com.bangkit.petme.ui.main.fragment.petscollection.PetCollectionAdapter
import com.bangkit.petme.viewmodel.AnnouncementViewModel
import com.bangkit.petme.viewmodel.ViewModelFactory

class NotificationFragment : Fragment() {
    private lateinit var binding: FragmentNotificationBinding
    private lateinit var announcementViewModel: AnnouncementViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        announcementViewModel = ViewModelProvider(requireActivity(), ViewModelFactory.getInstance(requireActivity().application)).get(
            AnnouncementViewModel::class.java)

        binding.rvAnnouncement.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAnnouncement.setHasFixedSize(true)

        announcementViewModel.getAnnouncement()

        announcementViewModel.listAnnouncement.observe(requireActivity()){
            showRecyclerView(it)
        }
    }

    private fun showRecyclerView(listAnnouncement: List<DataItem>){
        binding.rvAnnouncement.adapter = AnnouncementAdapter(listAnnouncement)
    }
}