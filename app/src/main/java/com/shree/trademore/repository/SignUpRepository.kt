package com.shree.trademore.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shree.trademore.model.SignUpModel
import com.shree.trademore.rest.ApiHelper
import com.shree.trademore.util.Resource
import java.lang.Exception

class SignUpRepository(private val apiHelper: ApiHelper) {
    private var signUpData = MutableLiveData<Resource<SignUpModel>>()
    fun getSignUpData(): LiveData<Resource<SignUpModel>> = signUpData
    suspend fun newUserRegistration(
        mobile: String,
        fullname: String,
        password: String
    ) {
        signUpData.postValue(Resource.loading(null))
        try {
            val photoResponse = apiHelper.registerNewUser(mobile, fullname, password)
            signUpData.postValue(Resource.success(photoResponse))
        } catch (e: Exception) {
            signUpData.postValue(Resource.error(e.toString(), null))
        }
    }
}