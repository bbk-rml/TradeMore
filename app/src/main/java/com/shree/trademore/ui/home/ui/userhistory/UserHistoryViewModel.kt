package com.shree.trademore.ui.home.ui.userhistory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserHistoryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is User History Fragment"
    }
    val text: LiveData<String> = _text
}