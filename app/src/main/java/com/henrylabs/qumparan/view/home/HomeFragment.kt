package com.henrylabs.qumparan.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.henrylabs.qumparan.data.remote.QumparanResource
import com.henrylabs.qumparan.databinding.FragmentHomeBinding
import com.henrylabs.qumparan.utils.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    val viewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun initUI() {}

    override fun initObserver() {
        viewModel.userLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is QumparanResource.Default -> {
                }
                is QumparanResource.Error -> {
                    showToast(it.message.toString())
                }
                is QumparanResource.Loading -> {
                    showToast("Loading")
                }
                is QumparanResource.Success -> {
                    showToast(it.data.toString())
                }
            }
        })
    }

    override fun initAction() {}

    override fun initData() {
        viewModel.fetchUsers()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}