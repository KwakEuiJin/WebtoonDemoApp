package com.kej.webtoondemoapp

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.kej.webtoondemoapp.databinding.FragmentWebviewBinding

class WebViewFragment(private val position: Int) : Fragment() {

    companion object {
        const val WEB_HISTORY = "WEB_HISTORY"
    }

    private var binding: FragmentWebviewBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_webview, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = requireActivity().getSharedPreferences(WEB_HISTORY, Context.MODE_PRIVATE)
        binding?.webView?.apply {
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                    sharedPreferences.edit().putString("$position", request?.url.toString()).apply()
                    return super.shouldOverrideUrlLoading(view, request)
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    binding?.progressbar?.isVisible = true
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding?.progressbar?.isVisible = false
                }
            }
            settings.javaScriptEnabled = true
            loadUrl("https://comic.naver.com/index")
        }

        binding?.lastButton?.setOnClickListener {
            val lastUrl = sharedPreferences.getString("$position", "")
            if (!lastUrl.isNullOrEmpty()) {
                binding?.webView?.loadUrl(lastUrl)
            }


        }
    }


    fun canGoBack(): Boolean? {
        return binding?.webView?.canGoBack()
    }


    fun goBack() {
        binding?.webView?.goBack()
    }

    override fun onStart() {
        super.onStart()
        Log.d("WebViewFragment", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("WebViewFragment", "onResume")

    }

    override fun onPause() {
        super.onPause()
        Log.d("WebViewFragment", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("WebViewFragment", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("WebViewFragment", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("WebViewFragment", "onDestroy")
    }


}