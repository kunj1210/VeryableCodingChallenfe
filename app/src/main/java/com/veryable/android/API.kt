package com.veryable.android

import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface API {

    //API
    @GET("veryable.json")
    fun veryableAPI(
    ): Call<ArrayList<AccountModel>>


}