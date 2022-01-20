package com.henrylabs.qumparan.view.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShowedPosts(
    val id: String,
    val title: String,
    val body: String,
    val userName: String,
    val userCompanyName: String,
    val userEmail: String,
    val userId : String
    ) : Parcelable