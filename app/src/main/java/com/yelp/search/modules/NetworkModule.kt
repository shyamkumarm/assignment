package com.yelp.search.modules

import androidx.viewbinding.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.yelp.search.network.ResponseApi
import com.yelp.search.network.request.SearchReq
import com.yelp.search.utils.ApiConstants
import okhttp3.Interceptor

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val sLogLevel =
    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE


private fun getLogInterceptor() = HttpLoggingInterceptor().apply { level = sLogLevel }

fun createNetworkClient() =
    retrofitClient(okHttpClient())

private fun okHttpClient() = OkHttpClient.Builder()
    .addInterceptor(getLogInterceptor()).apply { setTimeOutToOkHttpClient(this) }
     .addInterceptor(headersInterceptor(true)).build()


fun headersInterceptor(addAuthHeader: Boolean) = Interceptor { chain ->
    chain.proceed(
        chain.request().newBuilder()
            .addHeader(ApiConstants.CONTENT_TYPE, ApiConstants.APPLICATION_JSON)
            .also {
                if (addAuthHeader) {
                    /* Any authHeader is needed
                    * */
                }
            }
            .build()
    )
}

private fun retrofitClient(httpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
        .client(httpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()


private fun setTimeOutToOkHttpClient(okHttpClientBuilder: OkHttpClient.Builder) =
    okHttpClientBuilder.apply {
        readTimeout(30L, TimeUnit.SECONDS)
        connectTimeout(30L, TimeUnit.SECONDS)
        writeTimeout(30L, TimeUnit.SECONDS)
    }


private val retrofit: Retrofit = createNetworkClient()
private val DATA_API: ResponseApi = retrofit.create(ResponseApi::class.java)

val networkModule = module {
    single { DATA_API }
    single { SearchReq(get(), get()) }
}

val networkModules = listOf(networkModule)