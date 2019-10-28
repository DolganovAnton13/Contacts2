package com.antondolganov.contacts2.di.module

import android.content.Context
import dagger.Module
import javax.inject.Singleton
import dagger.Provides

@Module
class ContextModule(private val context: Context) {

    @Provides
    @Singleton
    fun context(): Context {
        return context
    }
}