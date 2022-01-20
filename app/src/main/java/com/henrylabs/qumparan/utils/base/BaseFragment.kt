package com.henrylabs.qumparan.utils.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseFragment : Fragment() {

    val job = Job()
    val uiScope = CoroutineScope(Dispatchers.Main + job)

    abstract fun initUI()
    abstract fun initObserver()
    abstract fun initAction()
    abstract fun initData()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showActionBar()
        initObserver()
        initAction()
        initUI()
        initData()
    }

    fun showToast(text: String, isLong: Boolean = false) {
        var duration = Toast.LENGTH_LONG
        if (!isLong)
            duration = Toast.LENGTH_SHORT
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    fun hideActionBar() {
        requireActivity().actionBar?.hide()
        (activity as AppCompatActivity?)?.supportActionBar?.hide()
    }

    fun showActionBar() {
        requireActivity().actionBar?.show()
        (activity as AppCompatActivity?)?.supportActionBar?.show()
    }

    fun viewGone(view: View) {
        view.visibility = View.GONE
    }

    fun viewVisible(view: View) {
        view.visibility = View.VISIBLE
    }

    fun enabledBackButton(status: Boolean) {
        // handle back on preview
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    isEnabled = status
                }
            }
            )
    }


}