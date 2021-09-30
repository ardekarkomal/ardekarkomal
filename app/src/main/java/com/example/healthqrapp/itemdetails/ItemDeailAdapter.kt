package com.example.healthqrapp.orderitemdetails

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.healthqrapp.R
import com.example.healthqrapp.databinding.RowItemDetailsBinding
import com.example.healthqrapp.model.ItemDetailsModel

class ItemDeailAdapter(var mList:ArrayList<ItemDetailsModel>):RecyclerView.Adapter<ItemDeailAdapter.ViewHolder>() {

    private lateinit var mContext:Context
    private lateinit var binding:RowItemDetailsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val layoutInflater = LayoutInflater.from(mContext)
        binding= DataBindingUtil.inflate(layoutInflater, R.layout.row_item_details,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            binding.tvTitle.text = mList[position].title
            binding.tvDesc.text = mList[position].description
            binding.tvUnitPrice.text = mList[position].unitPrice
            binding.tvQuantity.text = mList[position].quntity
            binding.tvTotalAmount.text = mList[position].total
    }
    override fun getItemCount() = mList.size


    class ViewHolder(itemView: RowItemDetailsBinding):RecyclerView.ViewHolder(itemView.root)
}