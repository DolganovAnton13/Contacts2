package com.antondolganov.contacts2.di

import com.antondolganov.contacts2.di.module.ContextModule
import com.antondolganov.contacts2.di.module.DatabaseModule
import com.antondolganov.contacts2.di.module.NetworkModule
import com.antondolganov.contacts2.di.module.RepositoryModule
import com.antondolganov.contacts2.viewmodel.ContactsListViewModel
import com.antondolganov.contacts2.viewmodel.ProfileViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, NetworkModule::class, DatabaseModule::class, ContextModule::class])
interface AppComponent {
    fun inject(contactsListViewModel: ContactsListViewModel)
    fun inject(profileViewModel: ProfileViewModel)
}