package com.example.sampleboilerplate.manager.api.masterdataapi

import com.example.sampleboilerplate.manager.api.ResponseState
import com.example.sampleboilerplate.manager.api.requestApi
import com.example.sampleboilerplate.model.api.masterdataapi.BankListResponse
import com.example.sampleboilerplate.model.api.masterdataapi.ParamListResponse
import javax.inject.Inject

class MasterDataRepository @Inject constructor(private val masterDataApi: MasterDataApi) {


    //info sample only
    suspend fun requestParamList() : ResponseState<ParamListResponse> {
        val response = requestApi(
            result = ParamListResponse::class.java
        ) {
            masterDataApi.postParamList()
        }

        if(response.isException()){
            return ResponseState.OnException(response)
        }


        if(!response.isSuccessful){
            return ResponseState.OnError(data = response)
        }

        return ResponseState.OnSuccess(response)
    }




}