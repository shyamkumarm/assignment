package com.yelp.search.network.request

import androidx.lifecycle.MutableLiveData
import com.yelp.search.model.DataModel
import com.yelp.search.network.ResponseApi
import com.yelp.search.network.response.Resource
import com.yelp.search.utils.ApiConstants
import com.yelp.search.utils.SystemUtils
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class SearchReq(
    private val systemUtils: SystemUtils,
    private val eventApi: ResponseApi
) : CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.IO

    fun getData(
        resData: MutableLiveData<Resource<DataModel>>

    ) {
        launch {
            resData.postValue(Resource.loading( null))
                if (systemUtils.isNetworkConnected()) {
                    val res = eventApi.getRequestDataAsync().await()
                    if (res.isSuccessful) {
                        resData.postValue(Resource.success(res.body()))
                    } else {
                        resData.postValue(Resource.error(res.errorBody()?.string()?:ApiConstants.UN_DEFINED_ERROR , null))
                    }
                } else {
                    resData.postValue(Resource.error(ApiConstants.NO_INTERNET, null))
                }
            }
        }
    }







