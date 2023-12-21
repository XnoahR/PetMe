package com.bangkit.petme.ui.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.petme.api.Response.DetailPetResponse
import com.bangkit.petme.preferences.Preferences
import com.bangkit.petme.repository.FavoriteRepository
import com.bangkit.petme.repository.PetsCollectionRepository
import kotlinx.coroutines.launch

class DetailPetViewModel (application: Application) : ViewModel() {
    private val petsCollectionRepository: PetsCollectionRepository = PetsCollectionRepository(application)
    private val favoriteRepository: FavoriteRepository = FavoriteRepository(application)
    private val preference: Preferences = Preferences(application)

    val detailPostPet = MutableLiveData<DetailPetResponse>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    fun getDetailPostPet(id : Int){
        viewModelScope.launch {
            startLoading()
            detailPostPet.value = petsCollectionRepository.getDetailPost(preference.getToken()!!, id)
            if(detailPostPet.value!!.isFav == true){
                favoriteYes()
            }else{
                favoriteNo()
            }
            stopLoading()
        }
    }

    private fun startLoading() {
        _isLoading.postValue(true)
    }

    fun stopLoading() {
        _isLoading.postValue(false)
    }

    private fun favoriteYes(){
        _isFavorite.postValue(true)
    }

    private fun favoriteNo(){
        _isFavorite.postValue(false)
    }

    fun addFavorite(id: Int){
        viewModelScope.launch {
            favoriteRepository.addFavorite(preference.getToken()!!, id)
        }
        favoriteYes()
    }

    fun deleteFavorite(id: Int){
        viewModelScope.launch {
            favoriteRepository.deleteFavorite(preference.getToken()!!, id)
        }
        favoriteNo()
    }

}