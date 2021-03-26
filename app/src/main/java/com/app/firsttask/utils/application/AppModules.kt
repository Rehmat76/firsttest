package com.app.firsttask.utils.application

import com.app.firsttask.datasource.network.RetrofitClientInstance
import com.app.firsttask.datasource.reporsitory.DataRepository
import com.app.firsttask.viewmodel.UserProfileViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    factory { RetrofitClientInstance(get()) }
    factory { DataRepository(get(),get()) }
}

val viewModelModules = module {
    viewModel { UserProfileViewModel(get()) }
}