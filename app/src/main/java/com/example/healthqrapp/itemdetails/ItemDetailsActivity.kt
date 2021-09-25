package com.example.healthqrapp.itemdetails

import androidx.databinding.DataBindingUtil
import com.example.healthqrapp.R
import com.example.healthqrapp.base.BaseActivity
import com.example.healthqrapp.databinding.ActivityItemDetailsBinding

class ItemDetailsActivity : BaseActivity() {

    private lateinit var itemDetailsDataBinding : ActivityItemDetailsBinding

    override fun getLayout()= R.layout.activity_item_details

    override fun init() {
        itemDetailsDataBinding = DataBindingUtil.setContentView(this,getLayout())
        setData()
    }

    override fun setClickListener() {
    }

    private fun setData(){
        itemDetailsDataBinding.tvOrderId.text = "90jsdjhdjshd32"
        itemDetailsDataBinding.tvCreateDate.text = "11-09-2021 11:09:23"
        itemDetailsDataBinding.tvTotal.text ="1245.09"
        itemDetailsDataBinding.tvTitle.text = "Life-Individual"
        itemDetailsDataBinding.tvDesc.text = "Life- individual individual  data"
        itemDetailsDataBinding.tvUnitPrice.text ="238873"
        itemDetailsDataBinding.tvQuantity.text = "1"
        itemDetailsDataBinding.tvTotalAmount.text = "1234"
    }
}