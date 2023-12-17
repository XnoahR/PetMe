package com.bangkit.petme.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.petme.api.Response.EditProfileResponse
import com.bangkit.petme.api.Response.PetCollectionResponseItem
import com.bangkit.petme.api.Response.UserProfileResponseItem
import com.bangkit.petme.preferences.Preferences
import com.bangkit.petme.repository.PetsCollectionRepository
import com.bangkit.petme.repository.ProfileRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : ViewModel() {
    private val preference: Preferences = Preferences(application)

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
}