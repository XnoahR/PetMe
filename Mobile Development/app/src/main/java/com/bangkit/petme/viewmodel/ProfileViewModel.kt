package com.bangkit.petme.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.petme.api.Response.EditProfileResponse
import com.bangkit.petme.api.Response.FavoritePetsResponseItem
import com.bangkit.petme.api.Response.UserProfileResponseItem
import com.bangkit.petme.preferences.Preferences
import com.bangkit.petme.repository.PetsCollectionRepository
import com.bangkit.petme.repository.ProfileRepository
import kotlinx.coroutines.launch


class ProfileViewModel(application: Application) : ViewModel() {
    private val petCollectionRepository: PetsCollectionRepository = PetsCollectionRepository(application)
    private val profileRepository: ProfileRepository = ProfileRepository(application)
    private val preference: Preferences = Preferences(application)

    private val _petsFavorite = MutableLiveData<List<FavoritePetsResponseItem>>()
    val petsFavorite: LiveData<List<FavoritePetsResponseItem>> = _petsFavorite

    private val _userProfile = MutableLiveData<List<UserProfileResponseItem?>>()
    val userProfile: LiveData<List<UserProfileResponseItem?>> = _userProfile

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> = _isEmpty

    init {
        notEmpty()
    }

    suspend fun editProfile(id: Int, name: String, email: String, phone: String, password: String): EditProfileResponse {
        startLoading()
        val response = profileRepository.editProfile(preference.getToken()!!, id, name, email, phone, password)
        stopLoading()
        return response
    }

    fun getFavoritePet(){
        viewModelScope.launch {
            startLoading()
            _petsFavorite.value = petCollectionRepository.getFavoritePets(preference.getToken()!!)
            _userProfile.value = profileRepository.getUserProfile(preference.getToken()!!)
            if(_petsFavorite.value.isNullOrEmpty()){
                empty()
            }else{
                notEmpty()
            }
            stopLoading()
        }
    }

    fun deleteToken(){
        preference.deleteToken()
    }
    private fun startLoading() {
        _isLoading.postValue(true)
    }

    private fun stopLoading() {
        _isLoading.postValue(false)
    }

    private fun empty() {
        _isEmpty.postValue(true)
    }

    private fun notEmpty() {
        _isEmpty.postValue(false)
    }
}