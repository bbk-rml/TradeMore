package com.shree.trademore.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shree.trademore.model.LoginModel
import com.shree.trademore.repository.LoginRepository
import com.shree.trademore.rest.ApiService
import com.shree.trademore.util.Resource
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel(val repository: LoginRepository):ViewModel() {
  //  var loginData = MutableLiveData<Resource<LoginModel>>()

//    fun login(username: String, password: String){
//        viewModelScope.launch {
//            loginData.postValue(Resource.loading(null))
//            try {
//                val loginResponse = repository.login(username,password)
//                loginData.postValue(Resource.success(loginResponse))
//            }catch (e: Exception){
//                loginData.postValue(Resource.error(e.toString(),null))
//            }
//        }
//    }

    fun login(username: String, password: String) {
        viewModelScope.launch { repository.login(username, password) }
    }

    fun getLoginData(): LiveData<Resource<LoginModel>> = repository.getPhotosList()
}