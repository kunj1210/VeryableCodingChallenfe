package com.veryable.android

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter_card(
    var context: Context,
    var card_ModelArrayList: ArrayList<CardModel>?


) : RecyclerView.Adapter<Adapter_card.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img_arrow_right_card: ImageView = itemView.findViewById(R.id.img_arrow_right_card)
        var txt_card_type: TextView = itemView.findViewById(R.id.txt_card_type)
        var txt_card_name: TextView = itemView.findViewById(R.id.txt_card_name)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Adapter_card.ViewHolder {

        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.adapter_cards, null)
        return Adapter_card.ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return card_ModelArrayList!!.size
    }

    override fun onBindViewHolder(holder: Adapter_card.ViewHolder, position: Int) {

        holder.img_arrow_right_card.setOnClickListener {
            val a = Bundle()
            a.putString("Card_name",card_ModelArrayList!!.get(position).account_name)
            a.putString("Card_type",card_ModelArrayList!!.get(position).desc)
            val intent  = Intent(context, Detail_cards::class.java)
            intent.putExtras(a)
            context.startActivity(intent)
        }

        for (i in 0 until card_ModelArrayList!!.size){
            if (card_ModelArrayList!!.get(i).account_type=="card"){
                holder.txt_card_type.text=card_ModelArrayList!!.get(position).account_name
                holder.txt_card_name.text=card_ModelArrayList!!.get(position).desc
            }
        }
    }
//    fun getArrayList(): ArrayList<String> {
//        return checkedArrayList
//    }


}








