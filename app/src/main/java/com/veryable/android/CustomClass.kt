package com.veryable.android

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class CustomClass {


    fun getCon(context: Context): API {


        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(interceptor)
        okHttpClient.connectTimeout(5, TimeUnit.MINUTES)
        okHttpClient.readTimeout(5, TimeUnit.MINUTES)
        okHttpClient.writeTimeout(100, TimeUnit.SECONDS)
        okHttpClient.retryOnConnectionFailure(true)
        okHttpClient.protocols(listOf(Protocol.HTTP_1_1))


//        val okHttpClient = OkHttpClient().newBuilder()
//            .connectTimeout(30000, TimeUnit.SECONDS)
//            .readTimeout(3000, TimeUnit.SECONDS)
//            .writeTimeout(3000, TimeUnit.SECONDS)
//            .build()


        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder().baseUrl(context.getString(R.string.url))
            .addConverterFactory(GsonConverterFactory.create(gson)).client(okHttpClient.build()).build()
        return retrofit.create(API::class.java)


    }

    fun isNetworkConnected(context: Context): Boolean {
        val cm =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return cm!!.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }

    fun getStringFromData(data: JSONObject?, key: String?): String {
        var result = ""
        if (data == null) {
            return ""
        } else {
            if (data.has(key)) {
                try {
                    result = data.getString(key!!)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        }
        return result

    }

}