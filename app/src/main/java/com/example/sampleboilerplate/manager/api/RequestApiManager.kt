package com.example.sampleboilerplate.manager.api

import com.example.sampleboilerplate.model.api.BaseApiResponse
import com.google.gson.Gson
import retrofit2.Response
import java.net.UnknownHostException


//info no status field
suspend fun <T: BaseApiResponse> requestApi(
    result: Class<T>,
    execute: suspend () -> Response<T>,
) : T {
    val responseState : T
    try{
        val response = execute()

        if (!response.isSuccessful) {
            val errorResponse = response.errorBody()
            responseState  = Gson().fromJson(errorResponse?.charStream(), result)
            return responseState
        }

        responseState = response.body()!!

        //todo another behavior if any

        responseState.isSuccessful = true

    } catch (e: Exception){
        e.message.let {
            //todo handle other exception
            return Gson().fromJson(generateExceptionErrorMessage(e.toString()), result)
        }
    }
    return responseState
}

fun generateExceptionErrorMessage(message: String?) : String {
    return "{\"status\":\"exception\",\"message\":\"$message\"}"
}
