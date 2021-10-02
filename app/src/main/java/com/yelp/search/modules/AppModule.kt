package com.yelp.search.modules

import com.yelp.search.utils.SystemUtils
import org.koin.dsl.module

private val appModule = module {
    single { SystemUtils(get()) }

}
val appModules = listOf(appModule)