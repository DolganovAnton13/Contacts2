package com.antondolganov.contacts2

import android.app.Application
import com.antondolganov.contacts2.di.AppComponent
import com.antondolganov.contacts2.di.module.ContextModule
import com.antondolganov.contacts2.di.DaggerAppComponent

class App : Application(){

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .contextModule(ContextModule(getApplicationContext()))
            .build();

    }
}