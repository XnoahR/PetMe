package com.bangkit.petme.preferences

import android.content.Context
import android.icu.lang.UProperty.AGE
import android.provider.SimPhonebookContract.SimRecords.PHONE_NUMBER

internal class Preferences(context: Context) {
    companion object {
        private const val PREFS_NAME = "petmepreferences"
        private const val USERNAME = "username"
        private const val TOKEN = "token"
        private const val LATITUDE = "latitude"
        private const val LONGITUDE = "longitude"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setToken(value: String) {
        val editor = preferences.edit()
        editor.putString(TOKEN, value)
        editor.apply()
    }

    fun getToken(): String? {
        return preferences.getString(TOKEN, "")
    }

    fun deleteToken(){
        val editor = preferences.edit()
        editor.putString(TOKEN, "")
        editor.apply()
    }

    fun setLatitude(value: Float){
        val editor = preferences.edit()
        editor.putFloat(LATITUDE, value)
        editor.apply()
    }

    fun getLatitude(): Float?{
        return preferences.getFloat(LATITUDE, 0.0F)
    }

    fun setLongitude(value: Float){
        val editor = preferences.edit()
        editor.putFloat(LONGITUDE, value)
        editor.apply()
    }


    fun getLongitude(): Float?{
        return preferences.getFloat(LONGITUDE, 0.0F)
    }

    fun setUsername(value: String){
        val editor = preferences.edit()
        editor.putString(USERNAME, value)
        editor.apply()
    }


    fun getUsername(): String?{
        return preferences.getString(USERNAME, "")
    }
}