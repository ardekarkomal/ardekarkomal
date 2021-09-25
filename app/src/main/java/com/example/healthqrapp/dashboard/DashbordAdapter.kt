package com.example.healthqrapp.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.healthqrapp.R
import com.example.healthqrapp.databinding.RowDashbordBinding
import com.example.healthqrapp.interfaces.EnumClicks
import com.example.healthqrapp.interfaces.OnRecyclerClickListener
import com.example.healthqrapp.model.DashbordModel

class DashbordAdapter(var mList:ArrayList<DashbordModel>,var onClick:OnRecyclerClickListener):RecyclerView.Adapter<DashbordAdapter.ViewHolder>() {

    private lateinit var mContext: Context
    private lateinit var binding: RowDashbordBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val layoutInflater = LayoutInflater.from(mContext)
        binding= DataBindingUtil.inflate(layoutInflater, R.layout.row_dashbord,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      binding.ivProfile.setImageResource(mList[position].image)
      binding.tvName.text = mList[position].text

      binding.llRootView.setOnClickListener {
          onClick.onRecyclerClick(EnumClicks.CELL_CLICK,position)
      }
    }

    override fun getItemCount() = mList.size

    class ViewHolder(itemView:RowDashbordBinding):RecyclerView.ViewHolder(itemView.root)
}