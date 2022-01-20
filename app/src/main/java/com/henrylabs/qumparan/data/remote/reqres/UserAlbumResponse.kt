package com.henrylabs.qumparan.data.remote.reqres


import com.google.gson.annotations.SerializedName

class UserAlbumResponse : ArrayList<UserAlbumResponse.UserAlbumResponseItem>(){
    data class UserAlbumResponseItem(
        @SerializedName("id")
        val id: Int,
        @SerializedName("title")
        val title: String,
        @SerializedName("userId")
        val userId: Int
    )
}