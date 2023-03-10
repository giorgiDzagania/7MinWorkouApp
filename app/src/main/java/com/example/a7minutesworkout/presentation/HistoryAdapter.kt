package com.example.a7minutesworkout.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a7minutesworkout.R
import com.example.a7minutesworkout.databinding.ItemHistoryRawBinding

class HistoryAdapter(private var items: ArrayList<String>)
    :RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHistoryRawBinding.inflate(LayoutInflater.from(
            parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date:String = items[position]
        holder.tvPosition.text = (position+1).toString()
        holder.tvItem.text = date

        if(position % 2 == 0){
            holder.llHistoryItemMain.setBackgroundColor(
                ContextCompat.getColor(holder.itemView.context,
                    R.color.lightGray
                ))
        }else{
            holder.llHistoryItemMain.setBackgroundColor(
                ContextCompat.getColor(holder.itemView.context,
                    R.color.white
                ))
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(binding: ItemHistoryRawBinding)
        : RecyclerView.ViewHolder(binding.root) {
        val llHistoryItemMain = binding.llHistoryItemMain
        val tvItem = binding.tvItem
        val tvPosition = binding.tvPosition
    }
}