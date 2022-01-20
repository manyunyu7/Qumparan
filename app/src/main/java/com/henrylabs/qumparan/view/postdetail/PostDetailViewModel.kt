package com.henrylabs.qumparan.view.postdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.henrylabs.qumparan.data.QumparanRepository
import com.henrylabs.qumparan.data.remote.QumparanResource
import com.henrylabs.qumparan.data.remote.reqres.PostCommentResponse
import kotlinx.coroutines.launch
import timber.log.Timber

class PostDetailViewModel(val repo: QumparanRepository) : ViewModel() {

    private var _commentLiveData = MutableLiveData<QumparanResource<PostCommentResponse?>>()
    val commentLiveData get() = _commentLiveData

    fun getPostComment(postId: String) {
        viewModelScope.launch {
            try {
                val res = repo.getPostComment(postId)
                Timber.d("users response $")
                if (res.isSuccessful) {
                    _commentLiveData.postValue(QumparanResource.Success(res.body()))
                } else {
                    _commentLiveData.postValue(QumparanResource.Error("Terjadi Kesalahan"))
                }
            } catch (e: Exception) {
                _commentLiveData.postValue(QumparanResource.Error(e.message.toString()))
            }
        }

    }
}