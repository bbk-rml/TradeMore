package com.shree.trademore.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class MySharePrefrence {
    public lateinit var sharedPreferences:SharedPreferences
    public lateinit var editor:SharedPreferences.Editor
    private val share_Data = "ShareData"
    private val USER_ID = "UserId"


    @SuppressLint("CommitPrefEdits")
    constructor(context: Context){
        sharedPreferences = context.getSharedPreferences(share_Data, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }


    companion object{
        var instance:MySharePrefrence? = null

        fun getInstance(context: Context):MySharePrefrence? {
            if(instance == null){
                instance = MySharePrefrence(context)
            }
            return instance
        }
    }

    fun setUserId(userId: String){
        editor.putString(USER_ID, userId)
        editor.apply()
    }

    fun getUserId(): String? {
        return sharedPreferences.getString(USER_ID,"")
    }
}