package com.example.healthqrapp.myorder

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthqrapp.R
import com.example.healthqrapp.base.BaseActivity
import com.example.healthqrapp.databinding.ActivityMyOrderBinding
import com.example.healthqrapp.model.MyOrderModel

class MyOrderActivity: BaseActivity() {

    private lateinit var myOrderBinding: ActivityMyOrderBinding
    private var myOrderList=ArrayList<MyOrderModel>()
    private lateinit var mAdapter: MyOrderAdapter

    override fun getLayout() = R.layout.activity_my_order

    override fun init() {
        myOrderBinding = DataBindingUtil.setContentView(this,getLayout())
        setList()
        setAdapter()
    }

    override fun setClickListener() {
    }

    private fun setList(){
        myOrderList.add(MyOrderModel("oredre_jhfjdiuieuere","pay_jshdjshjshjsdhsj","s1",
                   "123456","Mumbai","19-08-2021 11:09:37","l1","5453232327"))

        myOrderList.add(MyOrderModel("oredre_jhfjdiuieuere","pay_jshdjshjshjsdhsj","s1",
            "123456","Mumbai","19-08-2021 11:09:37","l1","5453232327"))

        myOrderList.add(MyOrderModel("oredre_jhfjdiuieuere","pay_jshdjshjshjsdhsj","s1",
            "123456","Mumbai","19-08-2021 11:09:37","l1","5453232327"))

    }

    private fun setAdapter(){
        mAdapter = MyOrderAdapter(myOrderList)
        myOrderBinding.rvMyOrder.layoutManager=LinearLayoutManager(this)
        myOrderBinding.rvMyOrder.adapter = mAdapter
    }
}