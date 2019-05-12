package com.example.apimusicaapp.data

import android.provider.BaseColumns

object DatabaseContract {

    object CancionEntry:BaseColumns{

        const val TABLE_NAME="song"

        const val COLUMN_NAME="name"
        const val COLUMN_ARTISTA="artista"
        const val COLUMN_ANIO="anio"
        const val COLUMN_ALBUM="album"
    }
}