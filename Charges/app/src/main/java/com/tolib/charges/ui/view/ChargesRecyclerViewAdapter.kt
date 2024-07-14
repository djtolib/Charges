package com.tolib.charges.ui.view

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.tolib.charges.R
import com.tolib.charges.data.model.ChargeItem

import com.tolib.charges.databinding.ChargeItemBinding

class ChargesRecyclerViewAdapter(
    private val values: List<ChargeItem>
) : RecyclerView.Adapter<ChargesRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ChargeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.nameView.text = item.charger.name
        holder.contentView.text = item.charger.address
        holder.itemView.setBackgroundResource(if (item.charger.busy) R.drawable.recyclerview_item_red else R.drawable.recyclerview_item_green)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ChargeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val nameView: TextView = binding.name
        val contentView: TextView = binding.content

    }

}