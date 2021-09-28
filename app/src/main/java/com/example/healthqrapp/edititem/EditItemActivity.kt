package com.example.healthqrapp.edititem

import android.content.Intent
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.healthqrapp.R
import com.example.healthqrapp.base.BaseActivity
import com.example.healthqrapp.dashboard.DashbordActivity
import com.example.healthqrapp.databinding.ActivityEditItemBinding
import com.example.healthqrapp.login.LoginActivity
import com.example.healthqrapp.myorder.MyOrderActivity
import com.example.healthqrapp.signup.base.SignUPActivity

class EditItemActivity :BaseActivity() {
    
    private lateinit var editItemBinding : ActivityEditItemBinding
    val category = resources.getStringArray(R.array.category)
    val itemType = resources.getStringArray(R.array.itemType)

    override fun getLayout()= R.layout.activity_edit_item

    override fun init() {
        editItemBinding = DataBindingUtil.setContentView(this,getLayout())
        setSpinnerAdapter()
    }

    override fun setClickListener() {
        editItemBinding.toolbar.tvSignup.setOnClickListener {
            val i = Intent(this, SignUPActivity::class.java)
            startActivity(i)
        }

        editItemBinding.toolbar.tvLogin.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }

        editItemBinding.toolbar.tvHome.setOnClickListener {
            val i = Intent(this, DashbordActivity::class.java)
            startActivity(i)
        }

        editItemBinding.toolbar.tvMyAddress.setOnClickListener {
            val i = Intent(this, MyOrderActivity::class.java)
            startActivity(i)
        }
    }

    private fun setSpinnerAdapter(){
        val categoryAdapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, category)
        editItemBinding.spCategory.adapter = categoryAdapter

        val itemTypeAdapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, itemType)
        editItemBinding.spCategory.adapter = itemTypeAdapter

    }
}