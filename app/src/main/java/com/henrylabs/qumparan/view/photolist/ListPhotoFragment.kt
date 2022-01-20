package com.henrylabs.qumparan.view.photolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.henrylabs.qumparan.data.remote.QumparanResource
import com.henrylabs.qumparan.data.remote.reqres.AlbumPhotoResponse
import com.henrylabs.qumparan.data.remote.reqres.PostCommentResponse
import com.henrylabs.qumparan.databinding.FragmentListPhotoBinding
import com.henrylabs.qumparan.utils.base.BaseFragment
import com.henrylabs.qumparan.view.home.ShowedPosts
import com.henrylabs.qumparan.view.postdetail.PostDetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ListPhotoFragment : BaseFragment() {


    private var _binding: FragmentListPhotoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val mAdapter by lazy { PhotoListAdapter() }

    val viewModel: PostDetailViewModel by viewModel()

    override fun initUI() {
        setupAdapter()
        setupRv()
        hideActionBar()

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupRv() {
        binding.rvPosts.let {
            it.adapter = mAdapter
            it.layoutManager = GridLayoutManager(requireContext(), 2)
            it.setHasFixedSize(true)
        }
    }

    private fun setupAdapter() {
        mAdapter.setupAdapterInterface(object : PhotoListAdapter.PostItemInterface {
            override fun onclick(model: AlbumPhotoResponse.AlbumPhotoResponseItem?) {
            }
        })
    }

    override fun initObserver() {
        viewModel.photosLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is QumparanResource.Default -> {
                }
                is QumparanResource.Error -> {
                    showToast(it.message.toString())
                }
                is QumparanResource.Loading -> {
                }
                is QumparanResource.Success -> {
                    it.data?.let {
                        setupPhoto(it)
                    }
                }
            }
        })
    }

    private fun setupPhoto(it: AlbumPhotoResponse) {
        mAdapter.setWithNewData(it.toMutableList())
        mAdapter.notifyDataSetChanged()
        showToast(mAdapter.itemCount.toString())
    }

    override fun initAction() {

    }

    override fun initData() {
        val albumId = arguments?.getString("albumId")
        viewModel.getPhotosByAlbum(albumId.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListPhotoBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }


}