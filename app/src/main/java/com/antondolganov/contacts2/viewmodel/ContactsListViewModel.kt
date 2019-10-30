package com.antondolganov.contacts2.viewmodel

import androidx.lifecycle.ViewModel
import com.antondolganov.contacts2.repository.DatabaseRepository
import com.antondolganov.contacts2.repository.DataRepository
import javax.inject.Inject
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.lifecycle.LiveData
import android.R.attr.data
import android.annotation.SuppressLint
import android.app.Application
import com.antondolganov.contacts2.data.model.Contact
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.antondolganov.contacts2.App
import io.reactivex.schedulers.Schedulers


class ContactsListViewModel (application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var data: DataRepository

    @Inject
    lateinit var database: DatabaseRepository
    lateinit var searchQuery: String
    val contactsLiveData = MutableLiveData<List<Contact>>()
    val statusLiveData = MutableLiveData<String>()
    val showLoadingLiveData = MutableLiveData<Boolean>()

    init {
        (application as App).appComponent.inject(this)
        loadContactsFromServer()
    }

    fun getContactsPagedList(): LiveData<PagedList<Contact>> {

        return LivePagedListBuilder(database.getContacts(), 20).build()
    }

    @SuppressLint("CheckResult")
    fun loadContactsFromServer() {

        showLoadingLiveData.postValue(true)
        data.getContacts()
            .subscribeOn(Schedulers.io())
            .subscribe({
                contactsLiveData.postValue(it)
                statusLiveData.postValue("Загружено")
                showLoadingLiveData.postValue(false)
            },{
                statusLiveData.postValue("Ошибка: ${it.message}")
                showLoadingLiveData.postValue(false)
            })
    }

    fun getResultsSearchQuery(): LiveData<PagedList<Contact>> {

        val pagedListConfig = PagedList.Config.Builder()
            .setPageSize(20)
            .build()

        return if (TextUtils.isDigitsOnly(searchQuery)) {
            LivePagedListBuilder(database.getContactsByPhone(searchQuery), pagedListConfig).build()
        } else {
            LivePagedListBuilder(database.getContactsByName(searchQuery), pagedListConfig).build()
        }

    }


    fun insertContactList(contacts: List<Contact>) {
        database.insertContactList(contacts)
    }

    fun getContactById(id: String): LiveData<Contact> {
        return database.getContactById(id)
    }

    fun deleteAllContacts() {
        database.deleteAllContacts()
    }

}