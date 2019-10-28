package com.antondolganov.contacts2.di.module

import android.content.Context
import dagger.Module
import com.antondolganov.contacts2.db.AppDatabase
import com.antondolganov.contacts2.db.dao.ContactDao
import dagger.Provides
import androidx.room.Room
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class DatabaseModule {

    @Singleton
    @Provides
    fun appDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun contactDao(appDatabase: AppDatabase): ContactDao {
        return appDatabase.contactDao()
    }
}