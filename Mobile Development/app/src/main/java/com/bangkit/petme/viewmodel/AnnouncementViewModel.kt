package com.bangkit.petme.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.petme.api.Response.AnnouncementResponse
import com.bangkit.petme.api.Response.DataItem
import com.bangkit.petme.api.Response.PostPetResponseItem
import com.bangkit.petme.api.Retrofit.ApiConfig
import com.bangkit.petme.preferences.Preferences
import com.bangkit.petme.repository.AnnouncementRepository
import com.bangkit.petme.repository.PetsCollectionRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnnouncementViewModel(application: Application) : ViewModel() {
    private val announcementRepository: AnnouncementRepository = AnnouncementRepository(application)
    private val preference: Preferences = Preferences(application)

    private val _listAnnouncement = MutableLiveData<List<DataItem>>()
    val listAnnouncement: LiveData<List<DataItem>> = _listAnnouncement

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> = _isEmpty

    init {
        notEmpty()
        getAnnouncement()
    }

    fun getAnnouncement() {
        startLoading()
        val client = ApiConfig.getApiService().getAnnouncement(preference.getToken()!!)
        client.enqueue(object : Callback<AnnouncementResponse> {
            override fun onResponse(
                call: Call<AnnouncementResponse>,
                response: Response<AnnouncementResponse>
            ) {
                stopLoading()
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    _listAnnouncement.value = responseBody.data
                } else {
//                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<AnnouncementResponse>, t: Throwable) {
                 stopLoading()
            }
        })
//        viewModelScope.launch {
//            startLoading()
//            _listAnnouncement.value = announcementRepository.getNotification(preference.getToken()!!)
//            if (_listAnnouncement.value.isNullOrEmpty()) {
//                empty()
//            } else {
//                notEmpty()
//            }
//            stopLoading()
//        }
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

    fun notEmpty() {
        _isEmpty.postValue(false)
    }
}

