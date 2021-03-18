package com.barlipdev.fitrite.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.barlipdev.fitrite.R
import com.barlipdev.fitrite.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by lazy {
        val activity = requireNotNull(this.activity){
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this,HomeViewModel.Factory(activity.application)).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater,container,false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.collectionList.adapter = HomeCollectionAdapter()

        return binding.root
    }
}