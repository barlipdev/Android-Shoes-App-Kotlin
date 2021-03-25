package com.barlipdev.fitrite.ui.home.shoe.size

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.barlipdev.fitrite.ui.home.shoe.size.sizechart.SizeChart

class SizeChartAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = SizeChart()
}