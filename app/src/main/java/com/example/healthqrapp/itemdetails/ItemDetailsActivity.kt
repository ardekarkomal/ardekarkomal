package com.example.healthqrapp.itemdetails

import android.content.Intent
import androidx.databinding.DataBindingUtil
import com.example.healthqrapp.R
import com.example.healthqrapp.base.BaseActivity
import com.example.healthqrapp.dashboard.DashbordActivity
import com.example.healthqrapp.databinding.ActivityItemDetailsBinding
import com.example.healthqrapp.login.LoginActivity
import com.example.healthqrapp.myorder.MyOrderActivity
import com.example.healthqrapp.signup.base.SignUPActivity

class ItemDetailsActivity : BaseActivity() {

    companion object{
        const val FROM="from"
        const val ORDER_ID="order id"
        const val CREATED_DATE="create date"
        const val TOTAL_AMOUNT="total amount"
        const val ITEM_IMAGE="item image"
        const val TITLE="title"
        const val DESCRIPTION="discription"
        const val UNIT_PRICE="unit price"
        const val QUANTITY="quantity"

    }
    private lateinit var itemDetailsDataBinding : ActivityItemDetailsBinding

    override fun getLayout()= R.layout.activity_item_details

    override fun init() {
        itemDetailsDataBinding = DataBindingUtil.setContentView(this,getLayout())
        setData()
    }

    override fun setClickListener() {
        itemDetailsDataBinding.toolbar.tvSignup.setOnClickListener {
            val i = Intent(this,SignUPActivity::class.java)
            startActivity(i)
        }

        itemDetailsDataBinding.toolbar.tvLogin.setOnClickListener {
            val i = Intent(this,LoginActivity::class.java)
            startActivity(i)
        }

        itemDetailsDataBinding.toolbar.tvHome.setOnClickListener {
            val i = Intent(this,DashbordActivity::class.java)
            startActivity(i)
        }

        itemDetailsDataBinding.toolbar.tvMyAddress.setOnClickListener {
            val i = Intent(this, MyOrderActivity::class.java)
            startActivity(i)
        }

        itemDetailsDataBinding.cvGoBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setData(){
        if(FROM=="Receipt") {
            itemDetailsDataBinding.tvOrderId.text = intent.getStringExtra(ORDER_ID)
            itemDetailsDataBinding.tvCreateDate.text = intent.getStringExtra(CREATED_DATE)
            itemDetailsDataBinding.tvTotal.text = intent.getStringExtra(TOTAL_AMOUNT)
            itemDetailsDataBinding.tvTitle.text = intent.getStringExtra(TITLE)
            itemDetailsDataBinding.tvDesc.text = intent.getStringExtra(DESCRIPTION)
            itemDetailsDataBinding.tvUnitPrice.text = intent.getStringExtra(UNIT_PRICE)
            itemDetailsDataBinding.tvQuantity.text = intent.getStringExtra(QUANTITY)
            itemDetailsDataBinding.tvTotalAmount.text = intent.getStringExtra(TOTAL_AMOUNT)
        }else{
            itemDetailsDataBinding.tvOrderId.text = "Order_23566"
            itemDetailsDataBinding.tvCreateDate.text = "11-2-2021 11:02:03"
            itemDetailsDataBinding.tvTotal.text ="1050"
            itemDetailsDataBinding.tvTitle.text = "Life-Insurance"
            itemDetailsDataBinding.tvDesc.text = "Life-Insurance individual policy"
            itemDetailsDataBinding.tvUnitPrice.text = "1000"
            itemDetailsDataBinding.tvQuantity.text = "1"
            itemDetailsDataBinding.tvTotalAmount.text = "1050"
        }
    }
}