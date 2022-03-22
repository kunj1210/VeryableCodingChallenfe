package com.veryable.android

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonObject
import com.veryable.android.databinding.ActivityPayoutsListBinding
import kotlinx.android.synthetic.main.activity_payouts_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import org.json.JSONArray


class PayoutsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPayoutsListBinding
    lateinit var adapterBankAccounts: Adapter_bank_accounts
    lateinit var adapterCard: Adapter_card
    lateinit var img_right_arrow: ImageView
    lateinit var img_right_arrow_card: ImageView
    lateinit var call: Call<ArrayList<AccountModel>>

    var accountModelArray: ArrayList<AccountModel> =
        ArrayList()
    var account_ModelArrayList: ArrayList<AccountModel> =
        ArrayList()
    var card_ModelArrayList: ArrayList<CardModel> =
        ArrayList()
    var cardModelArray: ArrayList<CardModel> =
        ArrayList()
    var id = ""
    var account_type = ""
    var account_name = ""
    var desc = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payouts_list)
        setUpAdapter()
        if (CustomClass().isNetworkConnected(this)) {
            apiCall()
        } else {
            Toast.makeText(
                this,
                getString(R.string.please_check_internet),
                Toast.LENGTH_SHORT
            ).show()
        }


    }

    private fun apiCall() {
        val dialog: Dialog = CustomLoader().loader(this)
        val api: API = CustomClass().getCon(this)

        call = api.veryableAPI(
        )
        call.enqueue(object : Callback<ArrayList<AccountModel>> {


            override fun onResponse(
                call: Call<ArrayList<AccountModel>>,
                response: Response<ArrayList<AccountModel>>
            ) {
                Log.e("Veryable", "" + response.body())
                dialog.dismiss()
//                        try {
                if (response.code() == 200) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            val arr: ArrayList<AccountModel> = response.body()!!
                            Log.e("response", "" + response.body())
                            for (i in 0 until arr.size) {


                                id = arr.get(i).id.toString()
                                account_type = arr.get(i).account_type
                                account_name = arr.get(i).account_name
                                desc = arr.get(i).desc
                                if (arr.get(i).account_type == "bank") {
                                    var account_model_content =
                                        AccountModel(
                                            id,
                                            account_type,
                                            account_name,
                                            desc
                                        )

                                    accountModelArray.add(account_model_content)
                                    Preference().setBankAccount(
                                        accountModelArray,
                                        this@PayoutsListActivity
                                    )
                                }
                                else if (arr.get(i).account_type.equals("card")){
                                    var card_model_content=CardModel(
                                        id,
                                        account_type, account_name, desc
                                    )
                                    cardModelArray.add(card_model_content)
                                    Preference().setCard(cardModelArray,this@PayoutsListActivity)
                                }
                            }
                        }
                    }
                } else {
                    Toast.makeText(
                        this@PayoutsListActivity,
                        getString(R.string.server_down),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<AccountModel>>, t: Throwable) {
                dialog.dismiss()
            }
        })

    }


    private fun setUpAdapter() {
        recyler_bank_accounts.layoutManager = LinearLayoutManager(this)
        recyler_cards.layoutManager = LinearLayoutManager(this)
//
        account_ModelArrayList = Preference().getBankAccount(this)!!
////        card_ModelArrayList = Preference().getCard(this)!!
//        card_ModelArrayList= Preference().getCard(this)!!
        Log.e("array", "" + accountModelArray.size)
        Log.e("array", "" + cardModelArray.size)


        adapterBankAccounts =
            Adapter_bank_accounts(
                this,
                account_ModelArrayList
            )
        recyler_bank_accounts.adapter = adapterBankAccounts
        card_ModelArrayList=Preference().getCard(this)!!

        adapterCard =
            Adapter_card(
                this,
                card_ModelArrayList
            )
        recyler_cards.adapter = adapterCard

    }

}