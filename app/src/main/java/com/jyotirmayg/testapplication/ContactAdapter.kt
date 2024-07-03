package com.jyotirmayg.testapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(private val dataList: List<ContactData>) : RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dataItem = dataList[position]
        holder.textViewName.text = dataItem.name
        holder.textViewNumber.text = dataItem.number
    }

    override fun getItemCount() = dataList.size


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.findViewById(R.id.txtName)
        val textViewNumber: TextView = itemView.findViewById(R.id.txtNumber)
    }
}
