package com.example.healthqrapp.addtocart

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.example.healthqrapp.R
import com.example.healthqrapp.lib.base.BaseActivity
import com.example.healthqrapp.lib.base.showMessage
import com.example.healthqrapp.checkout.CheckoutActivity
import com.example.healthqrapp.dashboard.DashbordActivity
import com.example.healthqrapp.databinding.ActivityAddToCartBinding
import com.example.healthqrapp.insurancedetails.InsuranceDetailsActivity
import com.example.healthqrapp.login.LoginActivity
import com.example.healthqrapp.myorder.MyOrderActivity
import com.example.healthqrapp.receipt.ReceiptActivity
import com.example.healthqrapp.signup.base.SignUPActivity
import com.example.healthqrapp.lib.utils.Constant

class AddToCartActivity: BaseActivity() {

    companion object{
        const val SIZE = "size"
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
     addToCartBinding.toolbar.tvItemCount.text =Constant.SELECTED_ITEM_COUNT
     setData()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    override fun setClickListener() {
        addToCartBinding.toolbar.tvSignup.setOnClickListener {
            val i = Intent(this, SignUPActivity::class.java)
            startActivity(i)
        }

        addToCartBinding.toolbar.tvLogin.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }

        addToCartBinding.toolbar.tvHome.setOnClickListener {
            val i = Intent(this, DashbordActivity::class.java)
            startActivity(i)
        }

        addToCartBinding.toolbar.tvMyAddress.setOnClickListener {
            val i = Intent(this, MyOrderActivity::class.java)
            startActivity(i)
        }

        addToCartBinding.cvContinueShopping.setOnClickListener {
            val i = Intent(this, InsuranceDetailsActivity::class.java)
            startActivity(i)
        }

        addToCartBinding.btnCheckout.setOnClickListener {
            if(addToCartBinding.btnCheckout.text.toString() == getString(R.string.checkout)) {
                val i = Intent(this, CheckoutActivity::class.java)
                startActivity(i)
            }else{
                showMessage(this,"Payment Done Successfully!!")
                val i = Intent(this, ReceiptActivity::class.java)
                i.putExtra(ReceiptActivity.TITLE,InsuranceDetailsActivity.TITLE)
                i.putExtra(ReceiptActivity.DESCRIPTION,InsuranceDetailsActivity.DESCRIPTION)
                i.putExtra(ReceiptActivity.UNIT_PRICE,unitPrice)
                i.putExtra(ReceiptActivity.TOTAL_AMOUNT,totalItemAmount)
                i.putExtra(ReceiptActivity.QUANTITY,totalItem)
                i.putExtra(ReceiptActivity.ITEM_IMAGE,InsuranceDetailsActivity.IMAGE)
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
                        totalItemAmount =(totalItemAmount.toInt() + unitPrice.toInt() + tax.toInt()).toString()
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
                totalItemAmount =(totalItemAmount.toInt() + unitPrice.toInt() + tax.toInt()).toString()
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