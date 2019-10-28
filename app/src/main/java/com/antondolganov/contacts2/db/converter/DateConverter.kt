package com.antondolganov.contacts2.db.converter

import androidx.room.TypeConverter
import java.util.*


class DateConverter {

    @TypeConverter
    fun fromDate(data: Date): Long {
        return data.getTime()
    }

    @TypeConverter
    fun fromLong(timestamp: Long): Date {
        return Date(timestamp)
    }
}