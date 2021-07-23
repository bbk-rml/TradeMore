package com.shree.trademore.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shree.trademore.model.SignUpModel
import com.shree.trademore.repository.SignUpRepository
import com.shree.trademore.util.Resource
import kotlinx.coroutines.launch

class SignUpViewModel(private val repository: SignUpRepository):ViewModel() {

//    var loginData = MutableLiveData<Resource<SignUpModel>>()
//
//    fun signUp(mobile: String,
//                 fullname: String,
//                 password: String){
//        viewModelScope.launch {
//            loginData.postValue(Resource.loading(null))
//            try {
//                val loginResponse = apiService.registerNewUser(mobile,fullname,password)
//                loginData.postValue(Resource.success(loginResponse))
//            }catch (e: Exception){
//                loginData.postValue(Resource.error(e.toString(),null))
//            }
//        }
//    }

    fun register( mobile: String,
               fullname: String,
               password: String) {
        viewModelScope.launch { repository.newUserRegistration(mobile, fullname,password) }
    }

    fun getSignUpData(): LiveData<Resource<SignUpModel>> = repository.getSignUpData()
}