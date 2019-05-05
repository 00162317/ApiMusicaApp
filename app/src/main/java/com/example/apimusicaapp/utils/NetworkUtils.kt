package com.example.apimusicaapp.utils

import android.net.Uri
import java.net.URL

class NetworkUtils {
    companion object {
        const val BASE_URL = "https://api-song.herokuapp.com/CancionModelo"

        fun buildURL() = URL(
            Uri.parse(BASE_URL)
                .buildUpon()
                .build()
                .toString()
        )

        fun getHTTPResult(url: URL) = url.readText()
    }
}