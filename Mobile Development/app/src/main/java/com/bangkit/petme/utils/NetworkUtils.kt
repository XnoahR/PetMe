package com.bangkit.petme.utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.provider.Settings

class NetworkUtils {
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    fun requestNetworkActivation(context: Context) {
        if (!isNetworkAvailable(context)) {
            // Jika tidak ada koneksi, buka pengaturan jaringan
            val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
            context.startActivity(intent)
        }
    }
}