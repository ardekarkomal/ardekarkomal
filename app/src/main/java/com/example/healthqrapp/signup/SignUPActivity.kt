package com.example.healthqrapp.signup.base

import android.content.Intent
import androidx.databinding.DataBindingUtil
import com.example.healthqrapp.R
import com.example.healthqrapp.lib.base.BaseActivity
import com.example.healthqrapp.lib.base.showMessage
import com.example.healthqrapp.databinding.ActivitySignupBinding
import com.example.healthqrapp.login.LoginActivity


class SignUPActivity :BaseActivity(){

    private lateinit var signUPDataBinding : ActivitySignupBinding

    override fun getLayout()= R.layout.activity_signup

    override fun init() {
     signUPDataBinding = DataBindingUtil.setContentView(this,getLayout())
     signUPDataBinding.toolbar.tvToolbarTitle.text = getString(R.string.sign_up)

    }

    override fun setClickListener() {
      signUPDataBinding.toolbar.llBack.setOnClickListener {
        finish()
      }

      signUPDataBinding.llSignIn.setOnClickListener {
      val i = Intent(this, LoginActivity::class.java)
      startActivity(i)
      }

      signUPDataBinding.btnSignUp.setOnClickListener{
          if (validation()) {
              val i = Intent(this, LoginActivity::class.java)
              startActivity(i)
          }
      }
    }

    private fun validation():Boolean{
        if(signUPDataBinding.etFirstName.text.toString() ==""){
            showMessage(this,"Please enter First Name")
            return false
        }else if(signUPDataBinding.etEmail.text.toString()==""){
            showMessage(this,"Please enter Email ID")
            return false
        }else if(signUPDataBinding.etMobileNumber.text.toString()=="" || signUPDataBinding.etMobileNumber.text.length <10){
             showMessage(this,"Please enter valid Mobile No.")
             return false
        }else if(signUPDataBinding.etPassword.text.toString()=="" || signUPDataBinding.etPassword.text.length < 8){
            showMessage(this,"Please enter valid Password.")
            return false
        }else if(signUPDataBinding.etCnfPassword.text.toString()=="" || signUPDataBinding.etCnfPassword.text.length <8){
            showMessage(this,"Please enter valid Confirm Password.")
            return false
        }else if(signUPDataBinding.etPassword.text.toString() != signUPDataBinding.etCnfPassword.text.toString()){
            showMessage(this,"Password and Confirm Password should be same.")
            return false
        }else{
            return true
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val i =Intent(this,LoginActivity::class.java)
        startActivity(i)
    }

}