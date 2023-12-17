package com.bangkit.petme.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bangkit.petme.api.Response.PetCollectionResponseItem
import com.bangkit.petme.model.PetCollection
import com.bangkit.petme.model.PetsCollection.petsCollection
import com.bangkit.petme.preferences.Preferences
import com.bangkit.petme.repository.PetsCollectionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PetsCollectionViewModel(application: Application) : ViewModel() {
    private val petsCollectionRepository: PetsCollectionRepository = PetsCollectionRepository(application)
    private val preference: Preferences = Preferences(application)

    private lateinit var petsCollection : List<PetCollectionResponseItem>

    private val _petsCollectionDisplay = MutableLiveData<List<PetCollectionResponseItem>>()
    val petsCollectionDisplay: LiveData<List<PetCollectionResponseItem>> = _petsCollectionDisplay

    private val _searchBar = MutableLiveData<String>()
    val searchBar: LiveData<String> = _searchBar


    init {
        _searchBar.value =""

        viewModelScope.launch {
            petsCollection = petsCollectionRepository.getPetsCollection(preference.getToken()!!)
            _petsCollectionDisplay.value = petsCollectionRepository.getPetsCollection(preference.getToken()!!)
        }
    }

    fun searchItem(value: String){
        _petsCollectionDisplay.value = petsCollection.filter { it.title.contains(value, ignoreCase = true) }
    }

    fun resetList(){
        _petsCollectionDisplay.value = petsCollection
    }

}


