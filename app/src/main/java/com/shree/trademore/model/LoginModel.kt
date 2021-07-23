package com.shree.trademore.model

data class LoginModel(
    val response_code:String,
    val comments:String,
    val status:Boolean
)

data class SignUpModel(
    val response_code:String,
    val comments:String,
    val status:Boolean
)