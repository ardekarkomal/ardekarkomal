package com.example.healthqrapp.itemlist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.healthqrapp.R
import com.example.healthqrapp.databinding.RowItemListBinding
import com.example.healthqrapp.lib.interfaces.EnumClicks
import com.example.healthqrapp.lib.interfaces.OnRecyclerClickListener
import com.example.healthqrapp.model.ItemListModel

class ItemListAdapter(var mList:ArrayList<ItemListModel>,var onClickListener:OnRecyclerClickListener) : RecyclerView.Adapter<ItemListAdapter.ViewHolder>() {

    private lateinit var mContext : Context
    private lateinit var binding : RowItemListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val layoutInflater = LayoutInflater.from(mContext)
        binding= DataBindingUtil.inflate(layoutInflater, R.layout.row_item_list,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        binding.ivItemImage.setImageResource(mList[position].image)
        binding.tvTitle.text = mList[position].name
        binding.tvDesc.text =mList[position].description
        binding.tvId.text = mList[position].id
        binding.tvUnitPrice.text = mList[position].unitPrice

        binding.cvEdit.setOnClickListener {
            onClickListener.onRecyclerClick(EnumClicks.CELL_EDIT_ITEM_CLICK,position)
        }

        binding.cvDelete.setOnClickListener {
            onClickListener.onRecyclerClick(EnumClicks.CELL_DELETE_ITEM_CLICK,position)
        }

        binding.rlRootView.setOnClickListener {
            onClickListener.onRecyclerClick(EnumClicks.CELL_ITEM_CLICK,position)
        }
    }

    override fun getItemCount()= mList.size

    class ViewHolder(itemView: RowItemListBinding):RecyclerView.ViewHolder(itemView.root)

}