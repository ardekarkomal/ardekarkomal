package com.example.healthqrapp.forgetpasswrd

import android.content.Intent
import androidx.databinding.DataBindingUtil
import com.example.healthqrapp.R
import com.example.healthqrapp.base.BaseActivity
import com.example.healthqrapp.databinding.ActivityForgetPasswordBinding

class ForgetPasswordActivity : BaseActivity() {

    private lateinit var forgetPasswordBinding: ActivityForgetPasswordBinding

    override fun getLayout()= R.layout.activity_forget_password

    override fun init() {
       forgetPasswordBinding = DataBindingUtil.setContentView(this,getLayout())
       forgetPasswordBinding.toolbar.tvToolbarTitle.text = getString(R.string.forget_password)
    }

    override fun setClickListener() {
      forgetPasswordBinding.toolbar.llBack.setOnClickListener {
         finish()
      }
    }
}