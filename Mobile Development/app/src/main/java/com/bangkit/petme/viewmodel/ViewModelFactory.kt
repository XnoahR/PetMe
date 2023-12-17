package com.bangkit.petme.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.petme.api.Response.DetailPetResponse
import com.bangkit.petme.ui.detail.DetailPetViewModel

class ViewModelFactory private constructor(private val mApplication: Application) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(application: Application): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(application)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(mApplication) as T
        }else if (modelClass.isAssignableFrom(PetsCollectionViewModel::class.java)){
            return PetsCollectionViewModel(mApplication) as T
        }else if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(mApplication) as T
        }else if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(mApplication) as T
        }else if (modelClass.isAssignableFrom(RegisterViewModel::class.java)){
            return RegisterViewModel(mApplication) as T
        }else if (modelClass.isAssignableFrom(DetailPetViewModel::class.java)){
            return DetailPetViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}