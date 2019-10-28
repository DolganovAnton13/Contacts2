package com.antondolganov.contacts2.db.converter

import androidx.room.TypeConverter
import com.antondolganov.contacts2.data.Temperament

class TemperamentConverter {

    @TypeConverter
    fun fromString(data: String): Temperament {
        return Temperament.valueOf(data)
    }

    @TypeConverter
    fun fromEnum(temperament: Temperament): String {
        return temperament.name
    }
}