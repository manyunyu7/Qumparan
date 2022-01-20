package com.henrylabs.qumparan.data

import com.feylabs.sawitjaya.data.remote.RemoteDataSource

class QumparanRepository(
    private val remoteDs: RemoteDataSource,
) {

    suspend fun getUsers() = remoteDs.getUsers()
    suspend fun getPosts() = remoteDs.getPosts()
    suspend fun getPostDetail(postId: String) = remoteDs.getPostDetail(postId)
    suspend fun getPostComment(postId: String) = remoteDs.getPostComment(postId)
    suspend fun getUserDetail(userId: String) = remoteDs.getUserDetail(userId)

}