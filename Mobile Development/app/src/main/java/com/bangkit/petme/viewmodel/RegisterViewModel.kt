package com.bangkit.petme.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.petme.api.Response.LoginResponse
import com.bangkit.petme.api.Response.RegisterResponse
import com.bangkit.petme.preferences.Preferences
import com.bangkit.petme.repository.AuthRepository

class RegisterViewModel(application: Application) : ViewModel() {
    private val authRepository: AuthRepository =
        AuthRepository(application)
    private val preference: Preferences = Preferences(application)

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    suspend fun register(name: String, email: String, phone: String, password: String): RegisterResponse {
        startLoading()
        val response = authRepository.register(name, email, phone, password)
        stopLoading()
        return response
    }

    private fun startLoading() {
        _isLoading.postValue(true)
    }

    fun stopLoading() {
        _isLoading.postValue(false)
    }
}