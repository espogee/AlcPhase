package com.ameyaw_ansu_proj.alc4phase_1.utils

import android.content.Context
import android.net.ConnectivityManager

object ConnectivityStateUtil {
    fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }
}