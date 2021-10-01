package com.example.healthqrapp.login

import android.content.Intent
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.healthqrapp.R
import com.example.healthqrapp.lib.base.BaseActivity
import com.example.healthqrapp.lib.base.showMessage
import com.example.healthqrapp.dashboard.DashbordActivity
import com.example.healthqrapp.databinding.ActivityNewLoginBinding
import com.example.healthqrapp.forgetpasswrd.ForgetPasswordActivity
import com.example.healthqrapp.signup.base.SignUPActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class LoginActivity : BaseActivity() {

    companion object {
        var loginType = "Admin"
    }

    private lateinit var loginDataBinding: ActivityNewLoginBinding
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 9001

    override fun getLayout() = R.layout.activity_new_login

    override fun init() {
        loginDataBinding = DataBindingUtil.setContentView(this, getLayout())
        loginDataBinding.toolbar.tvToolbarTitle.text = getString(R.string.login)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("YOUR_WEB_APPLICATION_CLIENT_ID")
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    override fun setClickListener() {
        loginDataBinding.toolbar.llBack.setOnClickListener {
            finishAffinity()
        }

        loginDataBinding.tvSignup.setOnClickListener {
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
            i.putExtra(DashbordActivity.LOGIN_TYPE, loginType)
            startActivity(i)
        }

        loginDataBinding.ivGoogle.setOnClickListener {
            signIn()
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

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(
                ApiException::class.java
            )
            // Signed in successfully
            val googleId = account?.id ?: ""
            Log.i("Google ID", googleId)

            val googleFirstName = account?.givenName ?: ""
            Log.i("Google First Name", googleFirstName)

            val googleLastName = account?.familyName ?: ""
            Log.i("Google Last Name", googleLastName)

            val googleEmail = account?.email ?: ""
            Log.i("Google Email", googleEmail)
            val googleProfilePicURL = account?.photoUrl.toString()
            Log.i("Google Profile Pic URL", googleProfilePicURL)

            val googleIdToken = account?.idToken ?: ""
            Log.i("Google ID Token", googleIdToken)

        } catch (e: ApiException) {
            // Sign in was unsuccessful
            Log.e(
                "failed code=", e.statusCode.toString()
            )
        }

    }
}