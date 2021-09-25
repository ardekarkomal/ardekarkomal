package com.example.healthqrapp.addtocart

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import com.example.healthqrapp.R
import com.example.healthqrapp.base.BaseActivity
import com.example.healthqrapp.checkout.CheckoutActivity
import com.example.healthqrapp.databinding.ActivityAddToCartBinding
import com.example.healthqrapp.insurancedetails.InsuranceDetailsActivity
import com.example.healthqrapp.itemdetails.ItemDetailsActivity
import com.example.healthqrapp.utils.Constant
import com.example.healthqrapp.utils.Utility

class AddToCartActivity: BaseActivity() {

    companion object{
        const val TITLE="title"
        const val SIZE = "size"
        const val PRICE="price"
        const val IMAGE="image"
        const val FROM="from"
    }

    private lateinit var addToCartBinding: ActivityAddToCartBinding
    private var totalItem=""
    private var unitPrice=""
    private var totalItemAmount=""
    private var tax=""

    override fun getLayout()= R.layout.activity_add_to_cart

    override fun init() {
     addToCartBinding = DataBindingUtil.setContentView(this,getLayout())
     setData()
    }

    @SuppressLint("SetTextI18n")
    override fun setClickListener() {
        addToCartBinding.btnCheckout.setOnClickListener {
            if(addToCartBinding.btnCheckout.text.toString() == getString(R.string.checkout)) {
                val i = Intent(this, CheckoutActivity::class.java)
                startActivity(i)
            }else{
                val i = Intent(this, ItemDetailsActivity::class.java)
                startActivity(i)
            }
        }

            addToCartBinding.tvMinus.setOnClickListener {
                if (totalItem.toInt() != 1) {
                    totalItem = (totalItem.toInt() - 1).toString()
                    addToCartBinding.tvTotalItem.text = totalItem
                    addToCartBinding.tvItemCount.text = "Cart($totalItem Items)"
                    val unitPrice1 = (unitPrice.toInt() * totalItem.toInt()).toString()
                    addToCartBinding.tvAmount.text = unitPrice1
                    tax = (5 * unitPrice1.toInt() / 100).toString()
                    addToCartBinding.tvTaxAmount.text = tax

                    if (totalItem == "1") {
                        addToCartBinding.tvTotalItemAmount.text = (unitPrice.toInt() + tax.toInt()).toString()
                        addToCartBinding.tvTotalPrice.text = unitPrice
                    } else {
                        addToCartBinding.tvTotalItemAmount.text =
                            (totalItemAmount.toInt() + unitPrice.toInt() + tax.toInt()).toString()
                        addToCartBinding.tvTotalPrice.text =
                            (totalItemAmount.toInt() + unitPrice.toInt() + tax.toInt()).toString()
                    }
                } else {
                    addToCartBinding.tvMinus.isClickable = false
                }
            }

        addToCartBinding.tvPlus.setOnClickListener {
            addToCartBinding.tvMinus.isClickable = true
            totalItem = (totalItem.toInt() + 1).toString()
            addToCartBinding.tvTotalItem.text = totalItem
            addToCartBinding.tvItemCount.text = "Cart($totalItem Items)"
            val unitPrice1 = (unitPrice.toInt() * totalItem.toInt()).toString()
            addToCartBinding.tvAmount.text = unitPrice1
            tax = (5* unitPrice1.toInt()/100).toString()
            addToCartBinding.tvTaxAmount.text = tax

            if (totalItem == "1") {
                addToCartBinding.tvTotalItemAmount.text = (unitPrice.toInt() + tax.toInt()).toString()
                addToCartBinding.tvTotalPrice.text = unitPrice
            }else{
                addToCartBinding.tvTotalItemAmount.text = (totalItemAmount.toInt() + unitPrice.toInt() + tax.toInt()).toString()
                addToCartBinding.tvTotalPrice.text = (totalItemAmount.toInt() + unitPrice.toInt() + tax.toInt()).toString()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setData(){
        addToCartBinding.ivImage.setImageResource(InsuranceDetailsActivity.IMAGE.toInt())
        addToCartBinding.tvSizeType.text = InsuranceDetailsActivity.SIZE
        addToCartBinding.tvUnitSizeType.text = InsuranceDetailsActivity.PRICE
        addToCartBinding.tvTitle.text =InsuranceDetailsActivity.TITLE
        totalItem = addToCartBinding.tvTotalItem.text.toString()
        addToCartBinding.tvItemCount.text = "Cart($totalItem Items)"
        addToCartBinding.tvAmount.text = InsuranceDetailsActivity.PRICE
        addToCartBinding.tvTaxAmount.text =( 5 * InsuranceDetailsActivity.PRICE.toInt()/100).toString()
        unitPrice = InsuranceDetailsActivity.PRICE
        tax = addToCartBinding.tvTaxAmount.text.toString()
        totalItemAmount = (unitPrice.toInt() + tax.toInt()).toString()
        addToCartBinding.tvTotalItemAmount.text = totalItemAmount
        addToCartBinding.tvTotalPrice.text = totalItemAmount

        if(intent.getStringExtra(FROM)=="CheckoutActivity"){
            addToCartBinding.btnCheckout.text = "Pay Now"
        }else {
            addToCartBinding.btnCheckout.text = getString(R.string.checkout)
        }

    }
}