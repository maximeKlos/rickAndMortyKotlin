package com.example.rickanmortykotlin.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.rickanmortykotlin.R
import com.example.rickanmortykotlin.interfaces.OnListClickListener
import com.example.rickanmortykotlin.model.Results


class MyAdapter(mDataset: List<Results>, onListClickListener: OnListClickListener) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    var mDataset: List<Results>
    var mOnListClickListener: OnListClickListener

    init {
        this.mDataset = mDataset
        this.mOnListClickListener = onListClickListener
    }

    class MyViewHolder(itemView: View, onListClickListener: OnListClickListener) : ViewHolder(itemView), View.OnClickListener {
        var tvName: TextView
        var tvStatus: TextView
        var imageChar: ImageView
        var onListClickListener: OnListClickListener

        init {
            tvName = itemView.findViewById(R.id.tvName)
            tvStatus = itemView.findViewById(R.id.tvStatus)
            imageChar = itemView.findViewById(R.id.imageChar)
            this.onListClickListener = onListClickListener
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onListClickListener.onListClickListener(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false), mOnListClickListener
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvName.text = mDataset.get(position).name
        val status = mDataset.get(position).status
        when(status){
            "Dead" -> holder.tvStatus.setTextColor(Color.RED)
            "Alive" -> holder.tvStatus.setTextColor(Color.GREEN)
            "unknown" -> holder.tvStatus.setTextColor(Color.GRAY)
        }
        holder.tvStatus.text = "•$status"
        holder.imageChar.load(mDataset.get(position).image)
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }
}