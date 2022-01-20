package com.henrylabs.qumparan.view.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henrylabs.qumparan.data.QumparanRepository
import com.henrylabs.qumparan.data.remote.QumparanResource
import com.henrylabs.qumparan.data.remote.reqres.UserAlbumResponse
import com.henrylabs.qumparan.data.remote.reqres.UserDetailResponse
import kotlinx.coroutines.launch
import timber.log.Timber

class UserProfileViewModel(val repo: QumparanRepository) : ViewModel() {

    private var _userLiveData = MutableLiveData<QumparanResource<UserDetailResponse?>>()
    val userLiveData get() = _userLiveData

    private var _userAlbumLiveData = MutableLiveData<QumparanResource<UserAlbumResponse?>>()
    val userAlbumLiveData get() = _userAlbumLiveData

    fun fetchUsers(userId: String) = viewModelScope.launch {
        _userLiveData.postValue(QumparanResource.Loading())
        try {
            val res = repo.getUserDetail(userId)
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

    fun fetchAlbum(userId: String) = viewModelScope.launch {
        _userAlbumLiveData.postValue(QumparanResource.Loading())
        try {
            val res = repo.getUserAlbum(userId)
            Timber.d("users response $")
            if (res.isSuccessful) {
                _userAlbumLiveData.postValue(QumparanResource.Success(res.body()))
            } else {
                _userAlbumLiveData.postValue(QumparanResource.Error("Terjadi Kesalahan"))
            }
        } catch (e: Exception) {
            _userAlbumLiveData.postValue(QumparanResource.Error(e.message.toString()))
        }
    }
}