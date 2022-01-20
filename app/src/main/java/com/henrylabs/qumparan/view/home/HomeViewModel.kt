package com.henrylabs.qumparan.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henrylabs.qumparan.data.QumparanRepository
import com.henrylabs.qumparan.data.remote.QumparanResource
import com.henrylabs.qumparan.data.remote.reqres.UserResponse
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(val repo: QumparanRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private var _userLiveData = MutableLiveData<QumparanResource<UserResponse?>>()
    val userLiveData get() = _userLiveData

    fun fetchUsers() = viewModelScope.launch {
        _userLiveData.postValue(QumparanResource.Loading())
        try {
            val res = repo.getUsers()
            Timber.d("users response $")
            if (res.isSuccessful) {
                _userLiveData.postValue(QumparanResource.Success(res.body()))
            } else {
                _userLiveData.postValue(QumparanResource.Error("Terjadi Kesalahan"))
            }
        } catch (e: Exception) {
            _userLiveData.postValue(QumparanResource.Error(e.message.toString()))
        }
    }

}