package com.henrylabs.qumparan.data.remote.reqres


import com.google.gson.annotations.SerializedName

class AlbumPhotoResponse : ArrayList<AlbumPhotoResponse.AlbumPhotoResponseItem>(){
    data class AlbumPhotoResponseItem(
        @SerializedName("albumId")
        val albumId: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("thumbnailUrl")
        val thumbnailUrl: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("url")
        val url: String
    )
}