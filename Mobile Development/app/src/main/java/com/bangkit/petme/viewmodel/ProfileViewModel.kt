package com.bangkit.petme.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.petme.model.PetCollection
import com.bangkit.petme.model.PetsCollection
import com.bangkit.petme.repository.PetsCollectionRepository


class ProfileViewModel(application: Application) : ViewModel() {
    private val petCollectionRepository: PetsCollectionRepository = PetsCollectionRepository(application)

    private val _petsCollection = MutableLiveData<List<PetCollection>>()
    val petsCollection: LiveData<List<PetCollection>> = _petsCollection

    init {
        _petsCollection.value = petCollectionRepository.getPetsCollection()
    }
}