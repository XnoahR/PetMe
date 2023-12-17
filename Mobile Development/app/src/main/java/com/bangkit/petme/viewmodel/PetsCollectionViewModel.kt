package com.bangkit.petme.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.petme.api.Response.PetCollectionResponseItem
import com.bangkit.petme.preferences.Preferences
import com.bangkit.petme.repository.PetsCollectionRepository
import kotlinx.coroutines.launch

class PetsCollectionViewModel(application: Application) : ViewModel() {
    private val petsCollectionRepository: PetsCollectionRepository = PetsCollectionRepository(application)
    private val preference: Preferences = Preferences(application)

    private lateinit var petsCollection : List<PetCollectionResponseItem>

    private val _petsCollectionDisplay = MutableLiveData<List<PetCollectionResponseItem>>()
    val petsCollectionDisplay: LiveData<List<PetCollectionResponseItem>> = _petsCollectionDisplay

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> = _isEmpty

    init {
        notEmpty()
    }

    fun getPetCollection(){
        viewModelScope.launch {
            startLoading()
            petsCollection = petsCollectionRepository.getPetsCollection(preference.getToken()!!)
            _petsCollectionDisplay.value = petsCollectionRepository.getPetsCollection(preference.getToken()!!)
            if(_petsCollectionDisplay.value.isNullOrEmpty()){
                empty()
            }else{
                notEmpty()
            }

            stopLoading()
        }
    }

    fun searchItem(value: String){
        _petsCollectionDisplay.value = petsCollection.filter { it.title.contains(value, ignoreCase = true) }
        if(_petsCollectionDisplay.value.isNullOrEmpty()){
            empty()
        }else{
            notEmpty()
        }
    }

    fun resetList(){
        if(_petsCollectionDisplay.value != null){
            _petsCollectionDisplay.value = petsCollection
        }
    }

    private fun startLoading() {
        _isLoading.postValue(true)
    }

    fun stopLoading() {
        _isLoading.postValue(false)
    }

    private fun empty() {
        _isEmpty.postValue(true)
    }

    private fun notEmpty() {
        _isEmpty.postValue(false)
    }

    fun deletePost(id: Int){
        viewModelScope.launch {
            petsCollectionRepository.deletePost(preference.getToken()!!, id)
        }
    }
}


