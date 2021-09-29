package com.example.healthqrapp.itemlist

import android.content.Intent
import android.view.View
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthqrapp.R
import com.example.healthqrapp.base.BaseActivity
import com.example.healthqrapp.base.showMessage
import com.example.healthqrapp.dashboard.DashbordActivity
import com.example.healthqrapp.databinding.ActivityItemListBinding
import com.example.healthqrapp.edititem.EditItemActivity
import com.example.healthqrapp.interfaces.EnumClicks
import com.example.healthqrapp.interfaces.OnRecyclerClickListener
import com.example.healthqrapp.login.LoginActivity
import com.example.healthqrapp.model.ItemListModel
import com.example.healthqrapp.myorder.MyOrderActivity
import com.example.healthqrapp.signup.base.SignUPActivity
import com.example.healthqrapp.utils.Constant

class ItemListActivity:BaseActivity(),OnRecyclerClickListener {

    private lateinit var itemListBinding: ActivityItemListBinding
    private var itemList = ArrayList<ItemListModel>()
    private lateinit var mAdapter : ItemListAdapter

    override fun getLayout() = R.layout.activity_item_list

    override fun init() {
        itemListBinding = DataBindingUtil.setContentView(this,getLayout())
        itemListBinding.toolbar.tvItem.visibility = View.VISIBLE
        itemListBinding.toolbar.tvItemCount.text = Constant.SELECTED_ITEM_COUNT
        setList()
        setAdapter()
    }

    override fun setClickListener() {
        itemListBinding.toolbar.tvSignup.setOnClickListener {
            val i = Intent(this, SignUPActivity::class.java)
            startActivity(i)
        }

        itemListBinding.toolbar.tvLogin.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }

        itemListBinding.toolbar.tvHome.setOnClickListener {
            val i = Intent(this, DashbordActivity::class.java)
            startActivity(i)
        }

        itemListBinding.toolbar.tvMyAddress.setOnClickListener {
            val i = Intent(this, MyOrderActivity::class.java)
            startActivity(i)
        }

        itemListBinding.toolbar.tvItem.setOnClickListener {
            val i = Intent(this,EditItemActivity::class.java)
            startActivity(i)
        }
    }

    private fun setList(){
        itemList.add(ItemListModel("1","Life-Individual","Life Insurance Indidual-policy","1000.00",R.drawable.insurance))
        itemList.add(ItemListModel("2","Life-Family floater","Life Insurance Family-policy","1000.00",R.drawable.insurance_family))
    }

    private fun setAdapter(){
        mAdapter = ItemListAdapter(itemList,this)
        itemListBinding.rvItemList.layoutManager = LinearLayoutManager(this)
        itemListBinding.rvItemList.adapter = mAdapter

    }

    override fun onRecyclerClick(where: EnumClicks, position: Int) {
        when(where){
            EnumClicks.CELL_EDIT_ITEM_CLICK->{
                val i= Intent(this,EditItemActivity::class.java)
                i.putExtra(EditItemActivity.FROM,"Item List")
                i.putExtra(EditItemActivity.CATEGORY,"Life Insurance")
                i.putExtra(EditItemActivity.ITEM_TYPE,"Individual")
                i.putExtra(EditItemActivity.NAME_TEXT,itemList[position].name)
                i.putExtra(EditItemActivity.DESCRIPTION,itemList[position].description)
                i.putExtra(EditItemActivity.UNIT_PRICE,itemList[position].unitPrice)
                i.putExtra(EditItemActivity.IMAGE,itemList[position].image)
                startActivity(i)
            }

            EnumClicks.CELL_DELETE_ITEM_CLICK->{
                if(itemList.size > 0){
                 itemList.removeAt(position)
                 mAdapter.notifyDataSetChanged()
                 showMessage(this,"Item deleted successfully!!")
                }
            }
            else -> {}
        }
    }
}