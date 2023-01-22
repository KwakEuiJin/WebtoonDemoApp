package com.kej.webtoondemoapp

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewpagerAdapter(private val activity: MainActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> {WebViewFragment(position)}
            1 -> {WebViewFragment(position)}
            else -> {WebViewFragment(position)}
        }
}