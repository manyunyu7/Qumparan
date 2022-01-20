package com.henrylabs.qumparan.di

import com.henrylabs.qumparan.view.home.HomeViewModel
import com.henrylabs.qumparan.view.postdetail.PostDetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { PostDetailViewModel(get()) }
}

