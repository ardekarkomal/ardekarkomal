package com.example.healthqrapp.myorder

import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthqrapp.R
import com.example.healthqrapp.base.BaseActivity
import com.example.healthqrapp.dashboard.DashbordActivity
import com.example.healthqrapp.databinding.ActivityMyOrderBinding
import com.example.healthqrapp.interfaces.EnumClicks
import com.example.healthqrapp.interfaces.OnRecyclerClickListener
import com.example.healthqrapp.itemdetails.ItemDetailsActivity
import com.example.healthqrapp.login.LoginActivity
import com.example.healthqrapp.model.MyOrderModel
import com.example.healthqrapp.signup.base.SignUPActivity
import com.example.healthqrapp.utils.Constant

class MyOrderActivity: BaseActivity(),OnRecyclerClickListener {

    companion object{
        const val TOTAL_AMOUNT="total amount"
        const val ITEM_IMAGE="item image"
        const val TITLE="title"
        const val DESCRIPTION="discription"
        const val UNIT_PRICE="unit price"
        const val QUANTITY="quantity"
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
                val i = Intent(this,ItemDetailsActivity::class.java)
                i.putExtra(ItemDetailsActivity.TITLE,intent.getStringExtra(TITLE))
                i.putExtra(ItemDetailsActivity.DESCRIPTION,intent.getStringExtra(DESCRIPTION))
                i.putExtra(ItemDetailsActivity.ITEM_IMAGE,intent.getStringExtra(ITEM_IMAGE))
                i.putExtra(ItemDetailsActivity.UNIT_PRICE,intent.getStringExtra(UNIT_PRICE))
                i.putExtra(ItemDetailsActivity.TOTAL_AMOUNT,intent.getStringExtra(TOTAL_AMOUNT))
                i.putExtra(ItemDetailsActivity.QUANTITY,intent.getStringExtra(QUANTITY))
                i.putExtra(ItemDetailsActivity.ORDER_ID,myOrderList[position].id)
                i.putExtra(ItemDetailsActivity.CREATED_DATE,myOrderList[position].createDate)
                startActivity(i)
            }
            else -> {}
        }
    }
}