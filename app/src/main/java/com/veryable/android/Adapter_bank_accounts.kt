package com.veryable.android

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter_bank_accounts(
    var context: Context,
    var account_ModelArrayList: ArrayList<AccountModel>?


) : RecyclerView.Adapter<Adapter_bank_accounts.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img_arrow_right: ImageView = itemView.findViewById(R.id.img_arrow_right)
        var txt_bank_account_type: TextView = itemView.findViewById(R.id.txt_bank_account_type)
        var txt_bank_name: TextView = itemView.findViewById(R.id.txt_bank_name)
        var txt_bank_account: TextView = itemView.findViewById(R.id.txt_bank_account)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Adapter_bank_accounts.ViewHolder {

        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.adapter_bank_accounts, null)
        return Adapter_bank_accounts.ViewHolder(view)

    }

    override fun getItemCount(): Int {
        Log.e("size",""+account_ModelArrayList!!.size)
        return account_ModelArrayList!!.size
    }

    override fun onBindViewHolder(holder: Adapter_bank_accounts.ViewHolder, position: Int) {
        holder.img_arrow_right.setOnClickListener {
            val a = Bundle()
            a.putString("Bank_name",account_ModelArrayList!!.get(position).account_name)
            a.putString("Bank_type",account_ModelArrayList!!.get(position).desc)
            val intent  = Intent(context, Detail::class.java)
            intent.putExtras(a)
            context.startActivity(intent)
        }
        for (i in 0 until account_ModelArrayList!!.size){
            if (account_ModelArrayList!!.get(i).account_type=="bank"){
                holder.txt_bank_account_type.text=account_ModelArrayList!!.get(position).account_name
                holder.txt_bank_name.text=account_ModelArrayList!!.get(position).desc
            }
        }


    }
//    fun getArrayList(): ArrayList<String> {
//        return checkedArrayList
//    }


}








