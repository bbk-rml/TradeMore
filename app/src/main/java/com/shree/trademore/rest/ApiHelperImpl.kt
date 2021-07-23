package com.shree.trademore.rest

import com.shree.trademore.model.LoginModel
import com.shree.trademore.model.SignUpModel


class ApiHelperImpl(private val apiService: ApiService): ApiHelper {
    override suspend fun registerNewUser(
        mobile: String,
        fullname: String,
        password: String
    ): SignUpModel = apiService.registerNewUser(mobile,fullname,password)

    override suspend fun login(username: String, password: String): LoginModel = apiService.login(username,password)
}