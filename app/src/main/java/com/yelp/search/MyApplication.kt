package com.yelp.search

import android.app.Application
import com.bumptech.glide.Glide
import com.yelp.search.modules.appModules
import com.yelp.search.modules.networkModules
import com.yelp.search.modules.viewModules
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class MyApplication : Application() {


    override fun onCreate() {
        super.onCreate()

         startKoin {
            androidLogger(Level.DEBUG)
            androidContext(applicationContext)
            modules(networkModules + viewModules + appModules)
        }
    }
}