package com.shree.trademore.rest

import com.shree.trademore.model.LoginModel
import com.shree.trademore.model.SignUpModel


interface ApiHelper {

    suspend fun registerNewUser(mobile:String,fullname:String,password:String):SignUpModel

    suspend fun login(username:String,password:String):LoginModel
}