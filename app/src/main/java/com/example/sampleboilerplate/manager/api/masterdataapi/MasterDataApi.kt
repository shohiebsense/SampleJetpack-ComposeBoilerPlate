package com.example.sampleboilerplate.manager.api.masterdataapi

import com.example.sampleboilerplate.model.api.masterdataapi.ParamListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface MasterDataApi {

    /**
     * info response:
     * datalist: object
     *  university: array
     */

    @POST("api/general/get/params")
    suspend fun postParamList() : Response<ParamListResponse>

}