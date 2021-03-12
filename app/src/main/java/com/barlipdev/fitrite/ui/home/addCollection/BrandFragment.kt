package com.barlipdev.fitrite.ui.home.addCollection

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
//test
    private val viewModel: BrandViewModel by lazy { 
        val activity = requireNotNull(this.activity){
            "You can only access the viewModel after onViewCreated()"
            
        }
        ViewModelProvider(this,BrandViewModel.Factory(activity.application)).get(BrandViewModel::class.java)
    }

    private var viewModelAdapter: BrandAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view,savedInstanceState)
        viewModel.brandList.observe(viewLifecycleOwner, Observer<List<Brand>>{
            brandList -> brandList?.apply {
                viewModelAdapter?.brands = brandList}
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentBrandAddcollectionBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_brand_addcollection,
            container,
            false
        )

        binding.setLifecycleOwner(viewLifecycleOwner)
        binding.viewModel = viewModel

        viewModelAdapter = BrandAdapter(BrandClick{
            val packageManager = context?.packageManager ?: return@BrandClick
        })

        binding.root.findViewById<RecyclerView>(R.id.brandlist).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        return binding.root
    }

    class BrandClick(val block: (Brand) -> Unit){
        fun onClick(brand: Brand) = block(brand)
    }

    class BrandAdapter(val callback: BrandClick) : RecyclerView.Adapter<BrandViewHolder>(){

        var brands: List<Brand> = emptyList()
        set(value){
            field = value
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
            val withDataBinding: BrandItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                BrandViewHolder.LAYOUT,
                parent,
                false)
            return BrandViewHolder(withDataBinding)
        }

        override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
            holder.viewDataBinding.also {
                it.brand = brands[position]
            }
        }

        override fun getItemCount() = brands.size

    }

    class BrandViewHolder(val viewDataBinding: BrandItemBinding) : RecyclerView.ViewHolder(viewDataBinding.root){
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.brand_item
        }
    }

}