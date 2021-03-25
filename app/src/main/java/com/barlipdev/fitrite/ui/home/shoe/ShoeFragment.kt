package com.barlipdev.fitrite.ui.home.shoe

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.barlipdev.fitrite.R
import com.barlipdev.fitrite.databinding.FragmentShoeBinding

class ShoeFragment : Fragment() {

    private val viewModel: ShoeViewModel by lazy {
        val activity = requireNotNull(this.activity){
            "You can only access the viewModel after onViewCreated()"
        }
        val args: ShoeFragmentArgs by navArgs()
        ViewModelProvider(this,ShoeViewModel.Factory(activity.application,args.currentBrand)).get(ShoeViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentShoeBinding.inflate(inflater, container, false)


        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.shoelistRecyclerView.adapter = ShoeAdapter(ShoeAdapter.ShoeListener{
            shoe -> viewModel.onShoeClicked(shoe)
        })

        viewModel.navigateToShoeSize.observe(viewLifecycleOwner, Observer { shoe ->
            shoe?.let {
                if (this.findNavController().currentDestination?.id == R.id.shoeFragment){
                    if (shoe!=null){
                        this.findNavController().navigate(ShoeFragmentDirections.actionShoeFragmentToShoeSizeSelector(shoe))
                        viewModel.onShoeNavigated()
                    }
                }
            }
        })

        return binding.root
    }

}