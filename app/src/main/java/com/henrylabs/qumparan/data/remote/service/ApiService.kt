package com.henrylabs.qumparan.data.remote.service

import com.henrylabs.qumparan.data.remote.reqres.PostResponse
import com.henrylabs.qumparan.data.remote.reqres.UserResponse
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

}