package com.hn.insave.utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtil {
    fun isNetworkAvailable(context: Context): Boolean {
        val connection: Boolean
        val connectivityManager = (context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        val info = connectivityManager.activeNetworkInfo
        connection = info != null
        return connection
    }
}