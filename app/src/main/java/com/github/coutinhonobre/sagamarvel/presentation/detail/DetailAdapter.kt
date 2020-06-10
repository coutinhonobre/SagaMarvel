package com.github.coutinhonobre.sagamarvel.presentation.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.coutinhonobre.sagamarvel.R
import kotlinx.android.synthetic.main.card_generico.view.*

class DetailAdapter(var genericList: MutableList<String>): RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(value: String){
            itemView.textViewGenerico.text = value
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DetailViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_generico, parent, false)
    )

    override fun getItemCount() = genericList.size

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bindView(genericList[position])
    }


}