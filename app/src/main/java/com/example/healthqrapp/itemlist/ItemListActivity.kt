package com.example.healthqrapp.itemlist

import android.content.Intent
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthqrapp.R
import com.example.healthqrapp.lib.base.BaseActivity
import com.example.healthqrapp.lib.base.showMessage
import com.example.healthqrapp.dashboard.DashbordActivity
import com.example.healthqrapp.databinding.ActivityItemListBinding
import com.example.healthqrapp.edititem.EditItemActivity
import com.example.healthqrapp.lib.interfaces.EnumClicks
import com.example.healthqrapp.lib.interfaces.OnRecyclerClickListener
import com.example.healthqrapp.login.LoginActivity
import com.example.healthqrapp.model.ItemListModel
import com.example.healthqrapp.myorder.MyOrderActivity
import com.example.healthqrapp.orderitemdetails.ItemDetailActivity
import com.example.healthqrapp.signup.base.SignUPActivity
import com.example.healthqrapp.lib.utils.Constant

class ItemListActivity:BaseActivity(),OnRecyclerClickListener {

    companion object{
     const val FROM="from"
     const val ID="id"
     const val NAME_TEXT="name"
     const val DESCRIPTION="description"
     const val UNITPRICE="unit price"
     const val IMAGEURL="image url"
    }

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
            val i = Intent(this,ItemListActivity::class.java)
            startActivity(i)
        }

        itemListBinding.ivAddNewItem.setOnClickListener {
            val i = Intent(this,EditItemActivity::class.java)
            startActivity(i)
        }
    }

    private fun setList(){
        itemList.add(ItemListModel("1","Life-Individual","Life Insurance Indidual-policy","1000.00",R.drawable.insurance))
        itemList.add(ItemListModel("2","Life-Family floater","Life Insurance Family-policy","1000.00",R.drawable.insurance_family))
    if(intent.getStringExtra(FROM)=="Edit Activity"){
        itemList.add(ItemListModel(intent.getStringExtra(ID).toString(),intent.getStringExtra(NAME_TEXT).toString(),intent.getStringExtra(DESCRIPTION).toString(),
        intent.getStringExtra(UNITPRICE).toString(),intent.getIntExtra(IMAGEURL,R.drawable.insurance)))

    }
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

            EnumClicks.CELL_ITEM_CLICK ->{
                val i= Intent(this,ItemDetailActivity::class.java)
                i.putExtra(ItemDetailActivity.ITEM_ID,itemList[position].id)
                i.putExtra(ItemDetailActivity.TITLE,itemList[position].name)
                i.putExtra(ItemDetailActivity.CREATED_DATE,"12/09/2021 11:09")
                i.putExtra(ItemDetailActivity.QUANTITY,"4")
                i.putExtra(ItemDetailActivity.DESCRIPTION,itemList[position].description)
                i.putExtra(ItemDetailActivity.TOTAL_AMOUNT,itemList[position].unitPrice)
                i.putExtra(ItemDetailActivity.UNIT_PRICE,itemList[position].unitPrice)
                i.putExtra(ItemDetailActivity.ITEM_IMAGE,itemList[position].image)
                i.putExtra(ItemDetailActivity.FROM,"")
                startActivity(i)
            }
            else -> {}
        }
    }
}