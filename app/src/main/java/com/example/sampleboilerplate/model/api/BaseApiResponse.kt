package com.example.sampleboilerplate.model.api

import com.google.gson.annotations.SerializedName


//info common required fields every response
abstract class BaseApiResponse(
    @SerializedName(value = "message")
    var message: String = "",
) {

    fun getLowerCaseKey(map: Map<String, Any>): Map<String, Any> {
        return map.mapKeys {
            it.key.lowercase()
        }
    }

    var isSuccessful: Boolean = false

    fun init(): BaseApiResponse {
        return this
    }

    fun isException(): Boolean {
        //todo handle it
        return false
    }

    override fun toString(): String {
        return message
    }

}
