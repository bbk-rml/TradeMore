package com.shree.trademore.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shree.trademore.repository.LoginRepository
import com.shree.trademore.repository.SignUpRepository
import com.shree.trademore.ui.login.LoginViewModel
import com.shree.trademore.ui.signup.SignUpViewModel


class ViewModelFactory(private val apiHelper: Any) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(apiHelper as LoginRepository) as T
        }
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel(apiHelper as SignUpRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}