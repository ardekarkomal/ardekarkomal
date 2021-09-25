package com.example.healthqrapp.insurancedetails

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.healthqrapp.R
import com.example.healthqrapp.databinding.RowInsuranceDetailsBinding
import com.example.healthqrapp.interfaces.EnumClicks
import com.example.healthqrapp.interfaces.OnRecyclerClickListener
import com.example.healthqrapp.model.InsuranceDetailsModel

class InsuranceDetailsAdapter(var mList:ArrayList<InsuranceDetailsModel>,var onclickListener:OnRecyclerClickListener) :RecyclerView.Adapter<InsuranceDetailsAdapter.ViewHolder>(){

    private lateinit var mContext: Context
    private lateinit var binding: RowInsuranceDetailsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val layoutInflater = LayoutInflater.from(mContext)
        binding= DataBindingUtil.inflate(layoutInflater, R.layout.row_insurance_details,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       binding.tvTitle.text = mList[position].title
       binding.tvDesc.text = mList[position].description
       binding.tvAmount.text = mList[position].amount
       binding.tvSizeType.text = mList[position].size
       binding.ivImage.setImageResource(mList[position].image)

       binding.cvRootView.setOnClickListener {
           onclickListener.onRecyclerClick(EnumClicks.CELL_CLICK,position)
       }
       binding.cvAddToCard.setOnClickListener {
            onclickListener.onRecyclerClick(EnumClicks.ADD_TO_CART_CLICK,position)
       }
    }

    override fun getItemCount()= mList.size

    class ViewHolder(itemview: RowInsuranceDetailsBinding):RecyclerView.ViewHolder(itemview.root)
}