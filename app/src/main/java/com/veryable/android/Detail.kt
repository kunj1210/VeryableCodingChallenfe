package com.veryable.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.adapter_bank_accounts.*

class Detail : AppCompatActivity() {
    lateinit var img_back_header:ImageView
    lateinit var txt_details_header:TextView
    lateinit var txt_accounts_header:TextView
    lateinit var txt_bank_name_details:TextView

    var account_name = ""
    var desc = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val bundle = intent.extras
        account_name = bundle!!.getString("" + "Bank_name")!!
        desc = bundle.getString("" + "Bank_type")!!
        init()
        clicks()
    }

    private fun clicks() {
        img_back_header.setOnClickListener {
            val intent  = Intent(this, PayoutsListActivity::class.java)
            startActivity(intent)
        }
        txt_done_detail.setOnClickListener {
            val intent  = Intent(this, PayoutsListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun init() {
        img_back_header=findViewById(R.id.img_back_header)
        txt_details_header=findViewById(R.id.txt_details_header)
        txt_accounts_header=findViewById(R.id.txt_accounts_header)
        txt_bank_name_details=findViewById(R.id.txt_bank_name_details)

        img_back_header.visibility= View.VISIBLE
        txt_details_header.visibility=View.VISIBLE
        txt_accounts_header.visibility=View.GONE

        txt_bank_account_type_details.text=account_name
        txt_bank_name_details.text=desc




    }
}