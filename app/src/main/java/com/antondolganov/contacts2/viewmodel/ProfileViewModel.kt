package com.antondolganov.contacts2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.antondolganov.contacts2.App
import com.antondolganov.contacts2.data.model.Contact
import com.antondolganov.contacts2.repository.DatabaseRepository
import javax.inject.Inject

class ProfileViewModel (application: Application) : AndroidViewModel(application){

    @Inject
    lateinit var database: DatabaseRepository

    init {
        (application as App).appComponent.inject(this)
    }

    fun getContactById(id: String): LiveData<Contact> {
        return database.getContactById(id)
    }
}