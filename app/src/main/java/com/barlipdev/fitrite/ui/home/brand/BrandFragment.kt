package com.barlipdev.fitrite.ui.home.brand

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.barlipdev.fitrite.R
import com.barlipdev.fitrite.databinding.FragmentBrandBinding

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

        val binding = FragmentBrandBinding.inflate(inflater,container,false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.brandlistRecyclerView.adapter = BrandAdapter(BrandAdapter.BrandListener {
            brand ->  viewModel.onBrandClicked(brand)
        })

        viewModel.navigateToShoes.observe(viewLifecycleOwner, Observer { brand ->
            brand?.let {
                if (this.findNavController().currentDestination?.id == R.id.brandFragment){
                    if(brand != null){
                        this.findNavController().navigate(BrandFragmentDirections.actionBrandFragmentToShoeFragment(brand))
                        viewModel.onShoesNavigated()
                    }
                }
            }
        })

        return binding.root

    }

}