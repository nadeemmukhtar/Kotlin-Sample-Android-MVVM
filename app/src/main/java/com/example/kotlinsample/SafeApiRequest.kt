package com.example.kotlinsample

import org.json.JSONObject
import retrofit2.Response

abstract class SafeApiRequest {

    suspend fun<T: Any> apiRequest(call: suspend () -> Response<T>) : T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        }
        else {
         val jsonObject = JSONObject(response.errorBody()?.string())
            throw ApiException(jsonObject.getString("Message"))

        }
    }

}

//class ApiException(message: String): IOException(message)