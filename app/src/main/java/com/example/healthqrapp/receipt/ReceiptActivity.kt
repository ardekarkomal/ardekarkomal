package com.example.healthqrapp.receipt

import android.content.Intent
import androidx.databinding.DataBindingUtil
import com.example.healthqrapp.R
import com.example.healthqrapp.base.BaseActivity
import com.example.healthqrapp.databinding.ActivityReceiptBinding
import com.example.healthqrapp.itemdetails.ItemDetailsActivity
import com.example.healthqrapp.myorder.MyOrderActivity
import com.example.healthqrapp.utils.Constant

class ReceiptActivity :BaseActivity(){

    companion object{
        const val TOTAL_AMOUNT="total amount"
        const val ITEM_IMAGE="item image"
        const val TITLE="title"
        const val DESCRIPTION="discription"
        const val UNIT_PRICE="unit price"
        const val QUANTITY="quantity"
    }

    private lateinit var receiptDataBinding : ActivityReceiptBinding

    override fun getLayout()= R.layout.activity_receipt

    override fun init() {
        receiptDataBinding = DataBindingUtil.setContentView(this, getLayout())
        receiptDataBinding.tvTransactionId.text="order_6273672637"
        receiptDataBinding.tvGrandTotal.text = "1000"
        receiptDataBinding.tvEmail.text = "bac@gmail.com"
        receiptDataBinding.tvStatus.text = "Captured"
        receiptDataBinding.toolbar.tvItemCount.text = Constant.SELECTED_ITEM_COUNT

    }

    override fun setClickListener(){
       receiptDataBinding.btnOrderDetail.setOnClickListener {
           val i= Intent(this,MyOrderActivity::class.java)
           i.putExtra(MyOrderActivity.TITLE,intent.getStringExtra(TITLE))
           i.putExtra(MyOrderActivity.DESCRIPTION,intent.getStringExtra(DESCRIPTION))
           i.putExtra(MyOrderActivity.QUANTITY,intent.getStringExtra(QUANTITY))
           i.putExtra(MyOrderActivity.TOTAL_AMOUNT,"1000")
           i.putExtra(MyOrderActivity.ITEM_IMAGE,intent.getStringExtra(ITEM_IMAGE))
           i.putExtra(MyOrderActivity.UNIT_PRICE,"1000")
           startActivity(i)
       }
    }
}