package com.barlipdev.fitrite.ui.home.addCollection

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.barlipdev.fitrite.R
import com.barlipdev.fitrite.databinding.BrandItemBinding
import com.barlipdev.fitrite.databinding.FragmentBrandAddcollectionBinding
import com.barlipdev.fitrite.domain.Brand
import com.barlipdev.fitrite.ui.home.addCollection.BrandViewModel

class BrandFragment : Fragment() {
    private val viewModel: BrandViewModel by lazy { 
        val activity = requireNotNull(this.activity){
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this,BrandViewModel.Factory(activity.application)).get(BrandViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentBrandAddcollectionBinding.inflate(inflater,container,false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.brandlist.adapter = BrandAdapter()

        return binding.root

    }

}