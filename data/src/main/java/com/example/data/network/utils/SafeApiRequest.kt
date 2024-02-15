package com.example.data.network.utils

import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

abstract class  SafeApiRequest   {

     suspend fun <T : Any> safeApiRequest(call: suspend () -> Response<T>): Response<T> {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response
        } else {
            val responseErr = response.errorBody()?.string()
            val message = StringBuilder()
            responseErr.let {
                try {
                    message.append(it?.let { it1 -> JSONObject(it1).getString("error") })
                } catch (e: JSONException) {

                }
            }
            Log.d("TAG", "safeApiRequest: $message")
            throw ApiException(message.toString())
        }
    }


}