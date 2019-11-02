package com.antondolganov.contacts2.repository

import com.antondolganov.contacts2.db.dao.ContactDao
import io.reactivex.schedulers.Schedulers
import io.reactivex.Completable
import com.antondolganov.contacts2.data.model.Contact
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import io.reactivex.functions.Action

class DatabaseRepository(val contactDao: ContactDao) {

    fun getContacts(): DataSource.Factory<Int, Contact> {
        return contactDao.getAllContacts()
    }

    fun getContactById(id: String): LiveData<Contact> {
        return contactDao.getContactById(id)
    }

    fun getContactsByName(query: String): DataSource.Factory<Int, Contact> {
        return contactDao.getContactsByName(query)
    }

    fun getContactsByPhone(query: String): DataSource.Factory<Int, Contact> {
        return contactDao.getContactsByPhone(query)
    }

    fun insertContactList(contacts: List<Contact>) {
        Completable.fromAction({
            contactDao.deleteAll()
            contactDao.insert(contacts)
        }).subscribeOn(Schedulers.io())
            .subscribe()
    }
}