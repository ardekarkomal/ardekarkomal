package com.example.healthqrapp.checkout

import android.content.Intent
import androidx.databinding.DataBindingUtil
import com.example.healthqrapp.R
import com.example.healthqrapp.addtocart.AddToCartActivity
import com.example.healthqrapp.lib.base.BaseActivity
import com.example.healthqrapp.lib.base.showMessage
import com.example.healthqrapp.dashboard.DashbordActivity
import com.example.healthqrapp.databinding.ActivityCheckoutBinding
import com.example.healthqrapp.login.LoginActivity
import com.example.healthqrapp.myorder.MyOrderActivity
import com.example.healthqrapp.signup.base.SignUPActivity
import com.example.healthqrapp.lib.utils.Constant

class CheckoutActivity : BaseActivity() {

    private lateinit var checkoutDataBinding : ActivityCheckoutBinding

    override fun getLayout()= R.layout.activity_checkout

    override fun init(){
        checkoutDataBinding = DataBindingUtil.setContentView(this,getLayout())
        checkoutDataBinding.toolbar.tvItemCount.text = Constant.SELECTED_ITEM_COUNT
    }

    override fun setClickListener() {
        checkoutDataBinding.toolbar.tvSignup.setOnClickListener {
            val i = Intent(this, SignUPActivity::class.java)
            startActivity(i)
        }

        checkoutDataBinding.toolbar.tvLogin.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }

        checkoutDataBinding.toolbar.tvHome.setOnClickListener {
            val i = Intent(this, DashbordActivity::class.java)
            startActivity(i)
        }

        checkoutDataBinding.toolbar.tvMyAddress.setOnClickListener {
            val i = Intent(this, MyOrderActivity::class.java)
            startActivity(i)
        }

        checkoutDataBinding.btnProceed.setOnClickListener {
          if(validation()) {
              val i = Intent(this, AddToCartActivity::class.java)
              i.putExtra(AddToCartActivity.FROM, "CheckoutActivity")
              startActivity(i)
          }
      }
    }

    private fun validation():Boolean{
        return if(checkoutDataBinding.etStreet.text.toString()==""){
            showMessage(this,"Please enter Street.")
            false
        }else if(checkoutDataBinding.etLocality.text.toString()==""){
            showMessage(this,"Please enter Locality.")
            false
        }else if(checkoutDataBinding.etZipCode.text.toString()=="" || checkoutDataBinding.etZipCode.text.length <6){
            showMessage(this,"Please enter valid ZipCode")
            false
        }else if(checkoutDataBinding.etCity.text.toString()==""){
            showMessage(this,"Please enter City.")
            false
        }else if(checkoutDataBinding.etPhoneNo.text.toString()=="" || checkoutDataBinding.etPhoneNo.text.length < 10){
            showMessage(this,"Please enter valid Phone No")
            false
        }else{
            true
        }
    }
}