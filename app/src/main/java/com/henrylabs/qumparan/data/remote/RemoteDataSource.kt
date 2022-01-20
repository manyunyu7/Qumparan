package com.feylabs.sawitjaya.data.remote

import com.henrylabs.qumparan.data.remote.service.ApiService

class RemoteDataSource(
    private val commonService: ApiService,
) {

    /**
     * get news
     * @param body,callback
     */
    suspend fun getUsers() = commonService.getUsers()


    suspend fun getPosts() = commonService.getPosts()

}