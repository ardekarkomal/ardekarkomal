package com.example.healthqrapp.dashboard

import android.content.Intent
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.healthqrapp.R
import com.example.healthqrapp.base.BaseActivity
import com.example.healthqrapp.databinding.ActivityDashbordBinding
import com.example.healthqrapp.insurancedetails.InsuranceDetailsActivity
import com.example.healthqrapp.interfaces.EnumClicks
import com.example.healthqrapp.interfaces.OnRecyclerClickListener
import com.example.healthqrapp.login.LoginActivity
import com.example.healthqrapp.model.DashbordModel
import com.example.healthqrapp.signup.base.SignUPActivity
import com.example.healthqrapp.utils.Constant

class DashbordActivity : BaseActivity(),OnRecyclerClickListener{

    private var mDashBordList = ArrayList<DashbordModel>()
    private lateinit var mAdapter : DashbordAdapter
    private lateinit var dashboardDataBinding: ActivityDashbordBinding

    override fun getLayout()= R.layout.activity_dashbord

    override fun init() {
      dashboardDataBinding = DataBindingUtil.setContentView(this,getLayout())
      setList()
      setAdapter()
    }

    override fun setClickListener(){
        dashboardDataBinding.toolbar.tvSignup.setOnClickListener {
            val i = Intent(this, SignUPActivity::class.java)
            startActivity(i)
        }

        dashboardDataBinding.toolbar.tvLogin.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }

        dashboardDataBinding.toolbar.tvHome.setOnClickListener {
            mDashBordList.clear()
            init()
        }
    }

    private fun setList(){
      mDashBordList.add(DashbordModel(R.drawable.ic_sign_in,"Insurance"))
      mDashBordList.add(DashbordModel(R.drawable.insurance3,"Finance"))
      mDashBordList.add(DashbordModel(R.drawable.ic_sign_in,"Insurance"))
      mDashBordList.add(DashbordModel(R.drawable.insurance1,"Insurance"))
      mDashBordList.add(DashbordModel(R.drawable.ic_sign_in,"Insurance"))
    }

    private fun setAdapter(){
     mAdapter = DashbordAdapter(mDashBordList,this)
     dashboardDataBinding.rvList.layoutManager = GridLayoutManager(this,3)
     dashboardDataBinding.rvList.adapter = mAdapter
    }

    override fun onRecyclerClick(where: EnumClicks, position: Int) {
        when(where){
         EnumClicks.CELL_CLICK->{
               Constant.SELECTED_ITEM_COUNT = (Constant.SELECTED_ITEM_COUNT.toInt() + 1).toString()
               dashboardDataBinding.toolbar.tvItemCount.text =Constant.SELECTED_ITEM_COUNT
               val i = Intent(this,InsuranceDetailsActivity::class.java)
               startActivity(i)
         }
         else -> {}
        }
    }
}