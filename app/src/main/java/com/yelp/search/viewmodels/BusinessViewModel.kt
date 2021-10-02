package com.yelp.search.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yelp.search.model.DataModel
import com.yelp.search.network.response.Resource
import com.yelp.search.network.request.SearchReq

class BusinessViewModel (private val eventRequestCall: SearchReq) : ViewModel() {

    var resData = MutableLiveData<Resource<DataModel>>()

    fun getData() =
        eventRequestCall.getData(resData)

}