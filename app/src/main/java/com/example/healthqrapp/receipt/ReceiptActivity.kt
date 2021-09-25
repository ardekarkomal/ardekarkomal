package com.example.healthqrapp.receipt

import androidx.databinding.DataBindingUtil
import com.example.healthqrapp.R
import com.example.healthqrapp.base.BaseActivity
import com.example.healthqrapp.databinding.ActivityReceiptBinding

class ReceiptActivity :BaseActivity(){

    private lateinit var receiptDataBinding : ActivityReceiptBinding

    override fun getLayout()= R.layout.activity_receipt

    override fun init() {
        receiptDataBinding = DataBindingUtil.setContentView(this, getLayout())
        receiptDataBinding.tvTransactionId.text="dgdgdgfdgffffff"
        receiptDataBinding.tvGrandTotal.text ="jhhjjjhj"
        receiptDataBinding.tvEmail.text = "dgjkjkd@gmail.com"
        receiptDataBinding.tvStatus.text = "jiiiiui"
    }

    override fun setClickListener(){

    }
}