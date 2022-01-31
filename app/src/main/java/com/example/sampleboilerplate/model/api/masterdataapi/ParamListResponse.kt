package com.example.sampleboilerplate.model.api.masterdataapi

import com.example.sampleboilerplate.model.api.BaseApiResponse
import com.example.sampleboilerplate.model.master.MSParam
import com.google.gson.annotations.SerializedName


//info this is just a sample response,
class ParamListResponse(
    @SerializedName(FIELD_DATA)
    val data: List<MSParam>
) : BaseApiResponse() {

    companion object {
        const val FIELD_DATA = "data"
    }

}