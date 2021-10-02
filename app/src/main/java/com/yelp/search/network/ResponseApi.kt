package com.yelp.search.network

import com.yelp.search.model.DataModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ResponseApi {

    @GET("v1/posts")
    fun getRequestDataAsync(): Deferred<Response<DataModel>>


}