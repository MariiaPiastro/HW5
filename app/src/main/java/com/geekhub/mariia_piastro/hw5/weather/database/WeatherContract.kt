package com.geekhub.mariia_piastro.hw5.weather.database

import android.provider.BaseColumns

object WeatherContract {

    object WeatherEntry : BaseColumns {
        const val TABLE_NAME = "weather"
        const val COLUMN_TEMP = "temp"
        const val COLUMN_HUMIDITY = "humidity"
        const val COLUMN_PRESSURE = "pressure"
        const val COLUMN_TEMP_MIN = "tempMin"
        const val COLUMN_TEMP_MAX = "tempMax"
        const val COLUMN_MAIN = "main"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_ICON = "icon"
        const val COLUMN_WIND_SPEED = "wind_speed"
        const val COLUMN_WIND_DEGREE = "wind_degree"
        const val COLUMN_DATE = "date"
    }

    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE IF NOT EXISTS ${WeatherEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY ," +
                "${WeatherEntry.COLUMN_TEMP} REAL," +
                "${WeatherEntry.COLUMN_HUMIDITY} REAL," +
                "${WeatherEntry.COLUMN_PRESSURE} REAL," +
                "${WeatherEntry.COLUMN_TEMP_MIN} REAL," +
                "${WeatherEntry.COLUMN_TEMP_MAX} REAL," +
                "${WeatherEntry.COLUMN_MAIN} TEXT," +
                "${WeatherEntry.COLUMN_DESCRIPTION} TEXT," +
                "${WeatherEntry.COLUMN_ICON} TEXT," +
                "${WeatherEntry.COLUMN_WIND_SPEED} REAL," +
                "${WeatherEntry.COLUMN_WIND_DEGREE} REAL," +
                "${WeatherEntry.COLUMN_DATE} TEXT)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${WeatherEntry.TABLE_NAME}"
}