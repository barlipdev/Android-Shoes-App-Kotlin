package com.barlipdev.fitrite.ui.home.shoe.size

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.barlipdev.fitrite.R
import com.barlipdev.fitrite.databinding.FragmentShoeSizeSelectorBinding
import com.google.android.material.tabs.TabLayoutMediator

class ShoeSizeSelector : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var sizeChartAdapter: SizeChartAdapter
    private lateinit var binding: FragmentShoeSizeSelectorBinding
    private val viewModel: ShoeSizeSelectorViewModel by lazy{
        val activity = requireNotNull(this.activity){
            "You can only access the viewModel after onViewCreated()"
        }
        val args: ShoeSizeSelectorArgs by navArgs()
        ViewModelProvider(this,ShoeSizeSelectorViewModel.Factory(activity.application,args.shoe)).get(ShoeSizeSelectorViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoeSizeSelectorBinding.inflate(inflater,container,false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        sizeChartAdapter = SizeChartAdapter(this)
        viewPager = binding.viewPager2
        viewPager.adapter = sizeChartAdapter

        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout,viewPager){
            tab, position->
            tab.text = sizeChartType(position)
        }.attach()
    }

    private fun sizeChartType(pos: Int): String = when (pos){
        0 -> "EU"
        1 -> "US"
        2 -> "UK"
        else -> "Wronga value"
    }

}