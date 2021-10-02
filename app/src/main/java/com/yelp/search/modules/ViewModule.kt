package com.yelp.search.modules

import com.yelp.search.viewmodels.BusinessViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module





val requestViewModel = module  {
    viewModel{ BusinessViewModel(get()) }

}

val viewModules = listOf(requestViewModel)

