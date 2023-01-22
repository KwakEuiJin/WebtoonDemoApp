package com.kej.webtoondemoapp

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.kej.webtoondemoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.webContainerViewpager.adapter = ViewpagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.webContainerViewpager) { tab, position ->
            tab.text = "position: $position"
        }.attach()
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.fragments[binding.webContainerViewpager.currentItem]
        if (currentFragment is WebViewFragment) {
            if (currentFragment.canGoBack() == true) {
                currentFragment.goBack()
            } else {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }
}