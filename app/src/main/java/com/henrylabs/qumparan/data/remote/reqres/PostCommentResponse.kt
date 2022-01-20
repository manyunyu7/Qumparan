package com.henrylabs.qumparan.data.remote.reqres


import com.google.gson.annotations.SerializedName

class PostCommentResponse : ArrayList<PostCommentResponse.PostCommentResponseItem>() {
    data class PostCommentResponseItem(
        @SerializedName("body")
        val body: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("postId")
        val postId: Int
    )
}