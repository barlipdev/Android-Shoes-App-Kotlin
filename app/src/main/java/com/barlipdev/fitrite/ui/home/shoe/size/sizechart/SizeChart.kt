package com.barlipdev.fitrite.ui.home.shoe.size.sizechart

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.lifecycle.Observer
import com.barlipdev.fitrite.R
import com.barlipdev.fitrite.databinding.FragmentSizeChartBinding

class SizeChart : Fragment() {

    private val viewModel: SizeChartViewModel by lazy {
        val activity = requireNotNull(this.activity){
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this,SizeChartViewModel.Factory(activity.application)).get(SizeChartViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSizeChartBinding.inflate(inflater,container,false)
        val numberPicker: NumberPicker

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        numberPicker = binding.sizePicker

        numberPicker.minValue = 0
        numberPicker.maxValue = 10

        viewModel.currentPos.observe(viewLifecycleOwner, Observer { pos ->
            pos?.let {
                numberPicker.value = pos
            }
        })

        numberPicker.setOnValueChangedListener{ numberPicker,oldVal,newVal ->
            viewModel.setCurrentSizePosition(newVal)
        }

        return binding.root
    }


}