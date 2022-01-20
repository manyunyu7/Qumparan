package com.henrylabs.qumparan.view.preview

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.henrylabs.qumparan.R
import com.henrylabs.qumparan.databinding.FragmentPreviewPhotoBinding
import com.henrylabs.qumparan.utils.base.BaseFragment


class PreviewPhotoFragment : BaseFragment() {

    private var _binding: FragmentPreviewPhotoBinding? = null
    private val binding get() = _binding as FragmentPreviewPhotoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPreviewPhotoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initUI() {
        val photoTitle = arguments?.getString("title") ?: ""
        val photoUrl = arguments?.getString("url") ?: ""

        binding.photoTitle.text = photoTitle
    }

    override fun initObserver() {
    }

    override fun initAction() {
    }

    override fun initData() {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = arguments?.getString("url")
        val webView = binding.webView
        val webSettings = webView.settings
        webView.settings.domStorageEnabled = true
        webSettings.javaScriptEnabled = true
        webSettings.allowFileAccess = true
        webSettings.allowContentAccess = true
        webView.clearCache(true)
        webView.loadUrl(url.toString())
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                webView.visibility = View.VISIBLE
            }

            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onReceivedError(
                view: WebView,
                errorCod: Int,
                description: String,
                failingUrl: String
            ) {
                showToast("Your Internet Connection May not be active Or $description")
            }

        }
    }

}