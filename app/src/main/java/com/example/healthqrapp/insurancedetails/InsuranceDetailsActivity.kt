package com.example.healthqrapp.insurancedetails

import android.content.Intent
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.healthqrapp.R
import com.example.healthqrapp.addtocart.AddToCartActivity
import com.example.healthqrapp.base.BaseActivity
import com.example.healthqrapp.databinding.ActivityInsuranceDetailsBinding
import com.example.healthqrapp.interfaces.EnumClicks
import com.example.healthqrapp.interfaces.OnRecyclerClickListener
import com.example.healthqrapp.model.InsuranceDetailsModel

class InsuranceDetailsActivity: BaseActivity(),OnRecyclerClickListener {

    companion object{
        var TITLE="title"
        var SIZE = "size"
        var PRICE="price"
        var IMAGE="image"
        var DESCRIPTION="description"
    }

    private lateinit var insuranceDetailsBinding: ActivityInsuranceDetailsBinding
    private var mInsurancelist=ArrayList<InsuranceDetailsModel>()
    private lateinit var mAdapter:InsuranceDetailsAdapter

    override fun getLayout() = R.layout.activity_insurance_details

    override fun init() {
        insuranceDetailsBinding = DataBindingUtil.setContentView(this,getLayout())
        setList()
        setAdapter()
    }

    override fun setClickListener() {
    }

    private fun setList(){
        mInsurancelist.add(
            InsuranceDetailsModel(R.drawable.insurance1,"Life-Family Floater"
            ,"1000","Life Insurance - Individual Policy","Regular"))
        mInsurancelist.add(
            InsuranceDetailsModel(R.drawable.insurance2,"Life-Individual"
                ,"1000","Life Insurance - Individual Policy","Regular"))
        mInsurancelist.add(
            InsuranceDetailsModel(R.drawable.insurance3,"Life-Individual"
                ,"1000","Life Insurance - Individual Policy","Regular"))
        mInsurancelist.add(
            InsuranceDetailsModel(R.drawable.insurance1,"Life-Individual"
                ,"1000","Life Insurance - Individual Policy","Regular"))

    }

    private fun setAdapter(){
        mAdapter = InsuranceDetailsAdapter(mInsurancelist,this)
        insuranceDetailsBinding.rvDetailsList.layoutManager = GridLayoutManager(this,3)
        insuranceDetailsBinding.rvDetailsList.adapter = mAdapter
    }

    override fun onRecyclerClick(where: EnumClicks, position: Int) {
      when(where){
          EnumClicks.CELL_ADD_TO_CART_CLICK ->{
              val i= Intent(this,AddToCartActivity::class.java)
              /* i.putExtra(AddToCartActivity.TITLE,mInsurancelist[position].title)
               i.putExtra(AddToCartActivity.SIZE,mInsurancelist[position].size)
               i.putExtra(AddToCartActivity.PRICE,mInsurancelist[position].amount)
               i.putExtra(AddToCartActivity.IMAGE,mInsurancelist[position].image)*/
               startActivity(i)

              // add details list data in constant
              TITLE = mInsurancelist[position].title
              SIZE = mInsurancelist[position].size
              IMAGE= mInsurancelist[position].image.toString()
              PRICE= mInsurancelist[position].amount
              DESCRIPTION = mInsurancelist[position].description

          }
          else ->{}
      }
    }
}