package com.example.healthqrapp.myorder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.healthqrapp.R
import com.example.healthqrapp.databinding.RowMyOrderBinding
import com.example.healthqrapp.model.MyOrderModel

class MyOrderAdapter(var mList: ArrayList<MyOrderModel>) :
    RecyclerView.Adapter<MyOrderAdapter.ViewHolder>() {

    private lateinit var mContext: Context
    private lateinit var binding: RowMyOrderBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val layoutInflater = LayoutInflater.from(mContext)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.row_my_order, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        binding.tvID.text = mList[position].id
        binding.tvPaymentId.text = mList[position].paymetId
        binding.tvStreet.text = mList[position].street
        binding.tvZipCode.text = mList[position].zipCode
        binding.tvCity.text = mList[position].city
        binding.tvCreateDate.text = mList[position].createDate
        binding.tvLocality.text = mList[position].locality
        binding.tvPhoneNumber.text = mList[position].phoneNumber

    }

    override fun getItemCount() = mList.size

    class ViewHolder(itemView: RowMyOrderBinding) : RecyclerView.ViewHolder(itemView.root)

}