package com.bangkit.petme.ui.fragment.petscollection

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.petme.model.PetCollection
import com.bangkit.petme.model.PetsCollection.petsCollection
import com.bangkit.petme.repository.PetsCollectionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PetsCollectionViewModel(application: Application) : ViewModel() {
    private val petCollectionRepository: PetsCollectionRepository = PetsCollectionRepository(application)

    private val _petsCollection = MutableLiveData<List<PetCollection>>()
    val petsCollection: LiveData<List<PetCollection>> = _petsCollection

    init {
        _petsCollection.value = petCollectionRepository.getPetsCollection()
    }
}


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
        if (modelClass.isAssignableFrom(PetsCollectionViewModel::class.java)) {
            return PetsCollectionViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}