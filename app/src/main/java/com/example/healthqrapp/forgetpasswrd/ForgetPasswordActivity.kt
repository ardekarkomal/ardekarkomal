package com.example.healthqrapp.forgetpasswrd

import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.healthqrapp.R
import com.example.healthqrapp.lib.base.BaseActivity
import com.example.healthqrapp.lib.base.showMessage
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
        forgetPasswordBinding.tilEmail.visibility = View.VISIBLE
        forgetPasswordBinding.otpView.visibility = View.GONE

      forgetPasswordBinding.btnResetPassword.setOnClickListener {
          if(forgetPasswordBinding.etEmail.text.toString() !=""){
              forgetPasswordBinding.btnResetPassword.text = getString(R.string.reset_password)
              if(forgetPasswordBinding.btnResetPassword.text ==getString(R.string.reset_password)){
                  forgetPasswordBinding.tilEmail.visibility = View.GONE
                  forgetPasswordBinding.otpView.visibility = View.VISIBLE
                  if(forgetPasswordBinding.otpView.otp !=""){
                      showMessage(this,"Password reset successfully!!")
                      finish()
                  }else{
                      showMessage(this,"Please enter otp.")
                  }
              }
          }else{
              showMessage(this,"Please enter Email.")
          }
      }
    }
}