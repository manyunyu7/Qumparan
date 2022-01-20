package com.henrylabs.qumparan.view.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.henrylabs.qumparan.R
import com.henrylabs.qumparan.data.remote.QumparanResource
import com.henrylabs.qumparan.databinding.UserProfileFragmentBinding
import com.henrylabs.qumparan.utils.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class UserProfileFragment : BaseFragment() {

    val viewModel: UserProfileViewModel by viewModel()

    var _binding: UserProfileFragmentBinding? = null
    val binding get() = _binding as UserProfileFragmentBinding

    override fun initUI() {
        hideActionBar()
    }

    override fun initObserver() {
        viewModel.userLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is QumparanResource.Default -> {
                }
                is QumparanResource.Error -> {
                    showToast(it.message.toString(),true)
                }
                is QumparanResource.Loading -> {
                }
                is QumparanResource.Success -> {
                    val data = it.data
                    if (data != null) {
                        binding.tvName.text = data.name
                        binding.tvCompany.text = data.company.name.toString()
                        binding.tvEmail.value(data.email)

                        data.address.apply {
                            val fullAddress = "$street $suite, $city, $zipcode"
                            binding.tvAddress.value(fullAddress)
                        }
                    }
                }
            }
        })
    }

    override fun initAction() {
        val userId = arguments?.getString("userId") ?: ""
        viewModel.fetchUsers(userId)
    }

    override fun initData() {
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = UserProfileFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

}