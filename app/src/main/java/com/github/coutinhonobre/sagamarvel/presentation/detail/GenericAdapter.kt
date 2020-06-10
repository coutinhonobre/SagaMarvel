package com.github.coutinhonobre.sagamarvel.presentation.detail

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.coutinhonobre.sagamarvel.R
import com.github.coutinhonobre.sagamarvel.data.model.Generica
import kotlinx.android.synthetic.main.card_generico.view.*

class GenericAdapter(var genericList: MutableList<Generica>): RecyclerView.Adapter<GenericAdapter.GenericViewHolder>() {

    class GenericViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(value: Generica){
            itemView.textViewGenerico.text = value.title
            itemView.textViewGenericoDescricao.text = value.description
            if (value.description.isNotEmpty()) itemView.textViewGenerico.setTypeface(null, Typeface.BOLD)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GenericViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_generico, parent, false)
    )

    override fun getItemCount() = genericList.size

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        holder.bindView(genericList[position])
    }


}