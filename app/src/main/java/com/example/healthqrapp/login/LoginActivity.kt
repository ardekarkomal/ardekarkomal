package com.example.healthqrapp.login

import android.content.Intent
import androidx.databinding.DataBindingUtil
import com.example.healthqrapp.R
import com.example.healthqrapp.base.BaseActivity
import com.example.healthqrapp.base.showMessage
import com.example.healthqrapp.dashboard.DashbordActivity
import com.example.healthqrapp.databinding.ActivityNewLoginBinding
import com.example.healthqrapp.forgetpasswrd.ForgetPasswordActivity
import com.example.healthqrapp.myorder.MyOrderActivity
import com.example.healthqrapp.myorder.MyOrderAdapter
import com.example.healthqrapp.signup.base.SignUPActivity

class LoginActivity : BaseActivity() {

    private lateinit var loginDataBinding: ActivityNewLoginBinding

    override fun getLayout() = R.layout.activity_new_login

    override fun init() {
        loginDataBinding = DataBindingUtil.setContentView(this, getLayout())
        loginDataBinding.toolbar.tvToolbarTitle.text = getString(R.string.login)
    }

    override fun setClickListener() {
        loginDataBinding.toolbar.llBack.setOnClickListener {
            finishAffinity()
        }

        loginDataBinding.llSignUp.setOnClickListener {
            val i = Intent(this, SignUPActivity::class.java)
            startActivity(i)
        }

        loginDataBinding.btnSignIn.setOnClickListener {
            if (validation()) {
                val i = Intent(this, DashbordActivity::class.java)
                startActivity(i)
            }
        }

        loginDataBinding.tvForgotPassword.setOnClickListener {
            val i = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(i)
        }

        loginDataBinding.tvSkipLogin.setOnClickListener {
            val i = Intent(this, DashbordActivity::class.java)
            startActivity(i)
        }

        loginDataBinding.ivGoogle.setOnClickListener {
          //  val i= Intent(this,MyOrderActivity::class.java)
          //  startActivity(i)
        }
    }

    private fun validation(): Boolean {
        return if (loginDataBinding.etEmail.text.toString() == "") {
            showMessage(this, "Please enter valid Email ID.")
            false
        } else if (loginDataBinding.etPassword.text.toString() == "" && loginDataBinding.etPassword.text.length < 8) {
            showMessage(this, "Password length should be 8 character.")
            false
        } else {
            true
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}