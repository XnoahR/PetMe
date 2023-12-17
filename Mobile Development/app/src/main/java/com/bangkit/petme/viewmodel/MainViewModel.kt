package com.bangkit.petme.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.petme.api.Response.PostPetResponseItem
import com.bangkit.petme.preferences.Preferences
import com.bangkit.petme.repository.PetsCollectionRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : ViewModel() {
    private val petsCollectionRepository: PetsCollectionRepository = PetsCollectionRepository(application)
    private val preference: Preferences = Preferences(application)

    private lateinit var postPet : List<PostPetResponseItem>

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _postPetDisplay = MutableLiveData<List<PostPetResponseItem>>()
    val postPetDisplay: LiveData<List<PostPetResponseItem>> = _postPetDisplay

    private val _stateType = MutableLiveData<String>()
    val stateType: LiveData<String> = _stateType

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> = _isEmpty

    init {
        _stateType.value = "All"
        notEmpty()
    }

    fun getPostPet(){
        viewModelScope.launch {
            startLoading()
            postPet = petsCollectionRepository.getPostPet(preference.getToken()!!)
            _postPetDisplay.value = petsCollectionRepository.getPostPet(preference.getToken()!!)
            if(_postPetDisplay.value.isNullOrEmpty()){
                empty()
            }else{
                notEmpty()
            }
            stopLoading()
        }
    }

    fun setStateType(value : String){
        _stateType.value = value
    }

    fun setLatitude(value: Float){
        preference.setLatitude(value)
    }

    fun setLongitude(value: Float){
        preference.setLongitude(value)
    }

    fun getLatitude() : Float {
        return preference.getLatitude()!!
    }

    fun getLongitude() : Float{
        return preference.getLongitude()!!
    }

    private fun startLoading() {
        _isLoading.postValue(true)
    }

    fun stopLoading() {
        _isLoading.postValue(false)
    }

    fun searchItem(value: String){
        startLoading()
        if(stateType.value == "All"){
            _postPetDisplay.value = postPet.filter { it.title.contains(value, ignoreCase = true) }
        }else if(stateType.value == "Cat"){
            _postPetDisplay.value = postPet.filter { it.title.contains(value, ignoreCase = true) }
            _postPetDisplay.value = _postPetDisplay.value?.filter { it.idAnimal == 1 }
        }else{
            _postPetDisplay.value = postPet.filter { it.title.contains(value, ignoreCase = true) }
            _postPetDisplay.value = _postPetDisplay.value?.filter { it.idAnimal == 2 }
        }
        if(_postPetDisplay.value.isNullOrEmpty()){
            empty()
        }else{
            notEmpty()
        }
        stopLoading()
    }

    fun resetList(){
        if(_stateType.value != "All"){
            _stateType.value = "All"
        }
    }

    private fun empty() {
        _isEmpty.postValue(true)
    }

    fun notEmpty() {
        _isEmpty.postValue(false)
    }

}