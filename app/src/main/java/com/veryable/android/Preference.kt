package com.veryable.android

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class Preference {
    private lateinit var preferences: SharedPreferences

    //Bank Account Details
    fun setBankAccount(list: ArrayList<AccountModel>, context: Context) {
        preferences =
            context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        val gson = Gson()
        val json: String = gson.toJson(list)
        editor.putString("bank_account", json)
        editor.apply()
    }

    fun getBankAccount(context: Context): ArrayList<AccountModel>? {
        preferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val gson = Gson()
        val json: String = preferences.getString("bank_account", null)!!
        val type: Type = object : TypeToken<ArrayList<AccountModel?>?>() {}.getType()
        return gson.fromJson(json, type)
    }

    //Card Details
    fun setCard(list: ArrayList<CardModel>, context: Context) {
        preferences =
            context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        val gson = Gson()
        val json: String = gson.toJson(list)
        editor.putString("card_details", json)
        editor.apply()
    }

    fun getCard(context: Context): ArrayList<CardModel>? {
        preferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val gson = Gson()
        val json: String = preferences.getString("card_details", null)!!
        val type: Type = object : TypeToken<ArrayList<CardModel?>?>() {}.getType()
        return gson.fromJson(json, type)
    }
}