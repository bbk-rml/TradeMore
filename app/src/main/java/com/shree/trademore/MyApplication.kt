package com.shree.trademore

import android.app.Application
import com.shree.trademore.repository.LoginRepository
import com.shree.trademore.repository.SignUpRepository
import com.shree.trademore.rest.ApiClient
import com.shree.trademore.rest.ApiHelperImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyApplication:Application() {
    // No need to cancel this scope as it'll be torn down with the process
    private val applicationScope = CoroutineScope(SupervisorJob())

    private val apiHelperImp by lazy { ApiHelperImpl(ApiClient.apiService) }
    val repositoryLogin by lazy { LoginRepository(apiHelperImp) }
    val repositorySignUp by lazy { SignUpRepository(apiHelperImp) }
}