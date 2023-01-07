package com.zeus.smartnews

import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context) {
    var mySharedPref: SharedPreferences =
        context.getSharedPreferences("filename", Context.MODE_PRIVATE)

    //this method will save the night mode state : true or false
    fun setNightModeState(state: Boolean) {
        val editor = mySharedPref.edit()
        editor.putBoolean("NightMode", state)
        editor.apply()
    }

    //this method will load the night mode state
    fun loadNightModeState(): Boolean {
        return mySharedPref.getBoolean("NightMode", false)
    }
}

