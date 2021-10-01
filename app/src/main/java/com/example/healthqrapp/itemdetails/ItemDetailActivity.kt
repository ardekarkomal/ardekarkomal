package com.example.healthqrapp.orderitemdetails

import android.content.Intent
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthqrapp.R
import com.example.healthqrapp.lib.base.BaseActivity
import com.example.healthqrapp.dashboard.DashbordActivity
import com.example.healthqrapp.databinding.ActivityItemDetailsBinding
import com.example.healthqrapp.itemlist.ItemListActivity
import com.example.healthqrapp.login.LoginActivity
import com.example.healthqrapp.model.ItemDetailsModel
import com.example.healthqrapp.myorder.MyOrderActivity
import com.example.healthqrapp.signup.base.SignUPActivity

class ItemDetailActivity : BaseActivity() {

    companion object{
        const val FROM="from"
        const val ITEM_ID="order id"
        const val CREATED_DATE="create date"
        const val TOTAL_AMOUNT="total amount"
        const val ITEM_IMAGE="item image"
        const val TITLE="title"
        const val DESCRIPTION="discription"
        const val UNIT_PRICE="unit price"
        const val QUANTITY="quantity"
    }

    private lateinit var itemDetailsDataBinding : ActivityItemDetailsBinding
    private var itemList=ArrayList<ItemDetailsModel>()
    private lateinit var mAdapter: ItemDeailAdapter

    override fun getLayout()= R.layout.activity_item_details

    override fun init() {
        itemDetailsDataBinding = DataBindingUtil.setContentView(this,getLayout())
        setData()
        setAdapter()
    }

    override fun setClickListener() {
        itemDetailsDataBinding.toolbar.tvSignup.setOnClickListener {
            val i = Intent(this,SignUPActivity::class.java)
            startActivity(i)
        }

        itemDetailsDataBinding.toolbar.tvLogin.setOnClickListener {
            val i = Intent(this,LoginActivity::class.java)
            startActivity(i)
        }

        itemDetailsDataBinding.toolbar.tvHome.setOnClickListener {
            val i = Intent(this, DashbordActivity::class.java)
            startActivity(i)
        }

        if(LoginActivity.loginType=="User") {
           itemDetailsDataBinding.toolbar.tvItem.visibility = View.GONE
        }else{
            itemDetailsDataBinding.toolbar.tvItem.visibility = View.VISIBLE
            itemDetailsDataBinding.toolbar.tvItem.setOnClickListener {
                val i = Intent(this, ItemListActivity::class.java)
                startActivity(i)
            }

        }

        itemDetailsDataBinding.toolbar.tvMyAddress.setOnClickListener {
            val i = Intent(this, MyOrderActivity::class.java)
            startActivity(i)
        }

        itemDetailsDataBinding.cvGoBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setData(){
        itemDetailsDataBinding.tvOrderIdText.text = "Item ID"
        itemDetailsDataBinding.tvOrderId.text = intent.getStringExtra(ITEM_ID)
        itemDetailsDataBinding.tvCreateDate.text = intent.getStringExtra(CREATED_DATE)
        itemDetailsDataBinding.tvTotal.text = intent.getStringExtra(TOTAL_AMOUNT)
        itemList.add(ItemDetailsModel(R.drawable.insurance,intent.getStringExtra(TITLE).toString(),
        intent.getStringExtra(DESCRIPTION).toString(),intent.getStringExtra(UNIT_PRICE).toString(),
        intent.getStringExtra(QUANTITY).toString(),intent.getStringExtra(TOTAL_AMOUNT).toString()))

        /*  if(FROM=="Receipt") {
              itemDetailsDataBinding.tvOrderId.text = intent.getStringExtra(ITEM_ID)
              itemDetailsDataBinding.tvCreateDate.text = intent.getStringExtra(CREATED_DATE)
              itemDetailsDataBinding.tvTotal.text = intent.getStringExtra(TOTAL_AMOUNT)
              itemList.add(ItemDetailsModel(R.drawable.insurance,intent.getStringExtra(TITLE).toString(),
                     intent.getStringExtra(DESCRIPTION).toString(),intent.getStringExtra(UNIT_PRICE).toString(),
                     intent.getStringExtra(QUANTITY).toString(),intent.getStringExtra(TOTAL_AMOUNT).toString()))
          }else{
              itemDetailsDataBinding.tvOrderId.text = "2"
              itemDetailsDataBinding.tvCreateDate.text = "11-2-2021 11:02:03"
              itemDetailsDataBinding.tvTotal.text ="1050"

              itemList.add(ItemDetailsModel(R.drawable.insurance,"Life-Insurance", "Life-Insurance individual policy",
                        "1000","1","1050"))
          }*/
    }

    private fun setAdapter(){
        mAdapter = ItemDeailAdapter(itemList)
        itemDetailsDataBinding.rvItemDetails.layoutManager = LinearLayoutManager(this)
        itemDetailsDataBinding.rvItemDetails.adapter = mAdapter
    }
}