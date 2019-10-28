package com.antondolganov.contacts2.di.module

import dagger.Module
import com.antondolganov.contacts2.db.dao.ContactDao
import dagger.Provides
import javax.inject.Singleton
import com.antondolganov.contacts2.api.Api
import com.antondolganov.contacts2.repository.DataRepository
import com.antondolganov.contacts2.repository.DatabaseRepository


@Module(includes = [DatabaseModule::class, NetworkModule::class])
class RepositoryModule {

    @Singleton
    @Provides
    fun dataRepository(api: Api): DataRepository {
        return DataRepository(api)
    }

    @Singleton
    @Provides
    fun databaseRepository(contactDao: ContactDao): DatabaseRepository {
        return DatabaseRepository(contactDao)
    }
}