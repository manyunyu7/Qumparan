package com.henrylabs.qumparan.view.postdetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.henrylabs.qumparan.R
import com.henrylabs.qumparan.data.remote.QumparanResource
import com.henrylabs.qumparan.data.remote.reqres.PostCommentResponse
import com.henrylabs.qumparan.databinding.FragmentHomeBinding
import com.henrylabs.qumparan.databinding.PostDetailFragmentBinding
import com.henrylabs.qumparan.utils.base.BaseFragment
import com.henrylabs.qumparan.view.home.ShowedPosts
import org.koin.android.viewmodel.ext.android.viewModel

class PostDetailFragment : BaseFragment() {


    private var _binding: PostDetailFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val mAdapter by lazy { PostCommentAdapter() }

    val viewModel: PostDetailViewModel by viewModel()

    override fun initUI() {

        setupAdapter()
        setupRv()

        hideActionBar()

        // load temporary UI based on previous data state
        arguments?.let {
            val model = it.getParcelable<ShowedPosts>("postModel")
            binding.apply {
                tvAuthor.text = model?.userName
                tvCompany.text = model?.userCompanyName
                tvContent.text = model?.body
                tvEmail.text = model?.userEmail
                tvTitle.text = model?.title

                cardProfile.setOnClickListener {
                    findNavController().navigate(
                        R.id.userProfileFragment,
                        bundleOf("userId" to model?.userId.toString())
                    )
                }
            }
        }
    }

    private fun setupRv() {
        binding.rvComment.let {
            it.adapter = mAdapter
            it.layoutManager = LinearLayoutManager(requireContext())
            it.setHasFixedSize(true)
        }
    }

    private fun setupAdapter() {
        mAdapter.setupAdapterInterface(object : PostCommentAdapter.PostItemInterface {
            override fun onclick(model: PostCommentResponse.PostCommentResponseItem?) {

            }

            override fun onUserClick(model: PostCommentResponse.PostCommentResponseItem?) {

            }

        })
    }

    override fun initObserver() {
        viewModel.commentLiveData.observe(viewLifecycleOwner, Observer {
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
                        setupComment(it)
                    }
                }
            }
        })
    }

    private fun setupComment(it: PostCommentResponse) {
        mAdapter.setWithNewData(it.toMutableList())
        mAdapter.notifyDataSetChanged()
        showToast(mAdapter.itemCount.toString())
    }

    override fun initAction() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun initData() {
        val model = arguments?.getParcelable<ShowedPosts>("postModel")
        viewModel.getPostComment(model?.id.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PostDetailFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }


}