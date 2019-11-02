package com.antondolganov.contacts2.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkState(var context: Context) {

    fun isOnline(): Boolean {
        var cm: ConnectivityManager? = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var netInfo: NetworkInfo? = cm?.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}