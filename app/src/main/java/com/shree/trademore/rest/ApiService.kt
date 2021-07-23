package com.shree.trademore.rest

import com.shree.trademore.model.LoginModel
import com.shree.trademore.model.SignUpModel
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("newcustomer_register/")
    suspend fun registerNewUser(@Field("mobile") mobile:String, @Field("fullname") fullname:String,@Field("password") password:String):SignUpModel

    @FormUrlEncoded
    @POST("login/")
    suspend fun login(@Field("username") username:String,@Field("password") password:String):LoginModel

}