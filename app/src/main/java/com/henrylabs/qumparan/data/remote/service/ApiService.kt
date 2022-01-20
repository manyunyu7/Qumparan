package com.henrylabs.qumparan.data.remote.service

import com.henrylabs.qumparan.data.remote.reqres.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface ApiService {

    /*
    Note Belajar :
    - Suspend fun harus return datanya langsung
    - Call return enqueue, untuk callback
    - Resource untuk digunakan bareng RxJava, Coroutines (suspend)
     */

    @GET("/users")
    suspend fun getUsers(): Response<UserResponse>

    @GET("/posts")
    suspend fun getPosts(): Response<PostResponse>

    @GET("posts/{postId}/")
    fun getPostDetail(
        @Path("postId") postId: String,
    ): Response<PostDetailResponse>

    @GET("users/{userId}/")
    suspend fun getUserDetail(
        @Path("userId") userId: String,
    ): Response<UserDetailResponse>

    @GET("users/{userId}/albums")
    suspend fun getUserAlbum(
        @Path("userId") userId: String,
    ): Response<UserAlbumResponse>

    @GET("albums/{albumId}/photos")
    suspend fun getPhotoByAlbum(
        @Path("albumId") albumId: String,
    ): Response<AlbumPhotoResponse>

    @GET("posts/{postId}/comments")
    suspend fun getPostComments(
        @Path("postId") postId: String,
    ): Response<PostCommentResponse>

}