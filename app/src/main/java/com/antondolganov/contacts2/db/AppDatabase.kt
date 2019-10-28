package com.antondolganov.contacts2.db

import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.TypeConverters
import com.antondolganov.contacts2.db.converter.TemperamentConverter
import com.antondolganov.contacts2.data.model.Contact
import com.antondolganov.contacts2.db.converter.DateConverter
import com.antondolganov.contacts2.db.dao.ContactDao


@Database(entities = [Contact::class], version = 1)
@TypeConverters(DateConverter::class, TemperamentConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao

}