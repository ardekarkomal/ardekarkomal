package com.example.healthqrapp.myorder

import android.content.Intent
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthqrapp.R
import com.example.healthqrapp.lib.base.BaseActivity
import com.example.healthqrapp.dashboard.DashbordActivity
import com.example.healthqrapp.databinding.ActivityMyOrderBinding
import com.example.healthqrapp.lib.interfaces.EnumClicks
import com.example.healthqrapp.lib.interfaces.OnRecyclerClickListener
import com.example.healthqrapp.itemlist.ItemListActivity
import com.example.healthqrapp.login.LoginActivity
import com.example.healthqrapp.model.MyOrderModel
import com.example.healthqrapp.orderitemdetails.OrderDetailsActivity
import com.example.healthqrapp.signup.base.SignUPActivity
import com.example.healthqrapp.lib.utils.Constant

class MyOrderActivity: BaseActivity(),OnRecyclerClickListener {

    companion object{
        const val FROM="from"
        const val TOTAL_AMOUNT="total amount"
        const val ITEM_IMAGE="item image"
        const val TITLE="title"
        const val DESCRIPTION="discription"
        const val UNIT_PRICE="unit price"
        const val QUANTITY="quantity"
        const val LOGIN_TYPE="login type"
    }
    private lateinit var myOrderBinding: ActivityMyOrderBinding
    private var myOrderList=ArrayList<MyOrderModel>()
    private lateinit var mAdapter: MyOrderAdapter

    override fun getLayout() = R.layout.activity_my_order

    override fun init() {
        myOrderBinding = DataBindingUtil.setContentView(this,getLayout())
        myOrderBinding.toolbar.tvItemCount.text = Constant.SELECTED_ITEM_COUNT
        setList()
        setAdapter()
    }

    override fun setClickListener() {
        myOrderBinding.toolbar.tvSignup.setOnClickListener {
            val i = Intent(this, SignUPActivity::class.java)
            startActivity(i)
        }

        myOrderBinding.toolbar.tvLogin.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }

        myOrderBinding.toolbar.tvHome.setOnClickListener {
            val i = Intent(this, DashbordActivity::class.java)
            startActivity(i)
        }

        if(LoginActivity.loginType=="User") {
            myOrderBinding.toolbar.tvItem.visibility = View.GONE

        }else{
            myOrderBinding.toolbar.tvItem.visibility = View.VISIBLE
            myOrderBinding.toolbar.tvItem.setOnClickListener {
                val i = Intent(this, ItemListActivity::class.java)
                startActivity(i)
            }
        }
    }

    private fun setList(){
        myOrderList.add(MyOrderModel("order_92189","pay_2324","s1",
                   "123456","Mumbai","19-08-2021 11:09:37","l1","5453232327"))

        myOrderList.add(MyOrderModel("order_1289","pay_8982","s1",
            "123456","Mumbai","19-08-2021 11:09:37","l1","5453232327"))

        myOrderList.add(MyOrderModel("order_12332","pay_2938","s1",
            "123456","Mumbai","19-08-2021 11:09:37","l1","5453232327"))

    }

    private fun setAdapter(){
        mAdapter = MyOrderAdapter(myOrderList,this)
        myOrderBinding.rvMyOrder.layoutManager=LinearLayoutManager(this)
        myOrderBinding.rvMyOrder.adapter = mAdapter
    }

    override fun onRecyclerClick(where: EnumClicks, position: Int) {
        when(where){
            EnumClicks.CELL_MY_ORDER_CLICK ->{
                val i = Intent(this,OrderDetailsActivity::class.java)
                i.putExtra(OrderDetailsActivity.FROM, intent.getStringExtra(FROM))
                i.putExtra(OrderDetailsActivity.TITLE,intent.getStringExtra(TITLE))
                i.putExtra(OrderDetailsActivity.DESCRIPTION,intent.getStringExtra(DESCRIPTION))
                i.putExtra(OrderDetailsActivity.ITEM_IMAGE,intent.getStringExtra(ITEM_IMAGE))
                i.putExtra(OrderDetailsActivity.UNIT_PRICE,intent.getStringExtra(UNIT_PRICE))
                i.putExtra(OrderDetailsActivity.TOTAL_AMOUNT,intent.getStringExtra(TOTAL_AMOUNT))
                i.putExtra(OrderDetailsActivity.QUANTITY,intent.getStringExtra(QUANTITY))
                i.putExtra(OrderDetailsActivity.ORDER_ID,myOrderList[position].id)
                i.putExtra(OrderDetailsActivity.CREATED_DATE,myOrderList[position].createDate)
                i.putExtra(OrderDetailsActivity.LOGIN_TYPE,intent.getStringExtra(LOGIN_TYPE))
                startActivity(i)
            }
            else -> {}
        }
    }
}