package com.henrylabs.qumparan.data

import com.feylabs.sawitjaya.data.remote.RemoteDataSource

class QumparanRepository(
    private val remoteDs: RemoteDataSource,
) {

   suspend fun getUsers() = remoteDs.getUsers()

}