package com.shree.trademore.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shree.trademore.model.LoginModel
import com.shree.trademore.rest.ApiHelper
import com.shree.trademore.util.Resource
import java.lang.Exception

class LoginRepository(private val apiHelper: ApiHelper) {

    private var photoList = MutableLiveData<Resource<LoginModel>>()
    fun getPhotosList(): LiveData<Resource<LoginModel>> = photoList
    suspend fun login(username: String, password: String) {
        photoList.postValue(Resource.loading(null))
        try {
            val photoResponse = apiHelper.login(username,password)
            photoList.postValue(Resource.success(photoResponse))
        } catch (e: Exception) {
            photoList.postValue(Resource.error(e.toString(), null))
        }
    }
}