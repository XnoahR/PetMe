package com.bangkit.petme.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.petme.api.Response.LoginResponse
import com.bangkit.petme.api.Response.PetCollectionResponseItem
import com.bangkit.petme.preferences.Preferences
import com.bangkit.petme.repository.AuthRepository
import com.bangkit.petme.repository.PetsCollectionRepository
import com.bumptech.glide.Glide.init
import kotlinx.coroutines.launch

class LoginViewModel (application: Application) : ViewModel() {
    private val authRepository: AuthRepository =
        AuthRepository(application)
    private val preference: Preferences = Preferences(application)

    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin: LiveData<Boolean> = _isLogin

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    suspend fun login(email: String, password: String): LoginResponse{
        startLoading()
        val response = authRepository.login(email, password)
        stopLoading()
        return response
    }

    private fun startLoading() {
        _isLoading.postValue(true)
    }

    fun stopLoading() {
        _isLoading.postValue(false)
    }

    fun saveToken(token: String){
        preference.setToken(token)
    }

    fun getToken(): String{
        return preference.getToken()!!
    }
}