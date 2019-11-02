package com.antondolganov.contacts2.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.antondolganov.contacts2.App
import com.antondolganov.contacts2.network.NetworkState
import com.antondolganov.contacts2.data.model.Contact
import com.antondolganov.contacts2.repository.DataRepository
import com.antondolganov.contacts2.repository.DatabaseRepository
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class ContactsListViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var data: DataRepository

    @Inject
    lateinit var database: DatabaseRepository

    var searchQuery: String? = null
    val contactsLiveData = MutableLiveData<List<Contact>>()
    val statusLiveData = MutableLiveData<String>()
    val showLoadingLiveData = MutableLiveData<Boolean>()
    var netWorkState = NetworkState(application)

    init {
        (application as App).appComponent.inject(this)
        showLoadingLiveData.postValue(false)
        loadContactsFromServer()

    }

    fun getContactsPagedList(): LiveData<PagedList<Contact>>
    {
        return LivePagedListBuilder(database.getContacts(), 20).build()
    }

    @SuppressLint("CheckResult")
    fun loadContactsFromServer() {
        if(netWorkState.isOnline()) {
            showLoadingLiveData.postValue(true)

            data.getContacts()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    contactsLiveData.postValue(it)
                    statusLiveData.postValue("Загружено")
                    showLoadingLiveData.postValue(false)
                }, {
                    statusLiveData.postValue("Ошибка: ${it.message}")
                    showLoadingLiveData.postValue(false)
                })
        }
        else
        {
            statusLiveData.postValue("Нет подключения к сети")
            showLoadingLiveData.postValue(false)
        }
    }

    fun getResultsSearchQuery(): LiveData<PagedList<Contact>> {
        return if (TextUtils.isDigitsOnly(searchQuery)) {
            LivePagedListBuilder(database.getContactsByPhone(searchQuery!!), 20).build()
        } else {
            LivePagedListBuilder(database.getContactsByName(searchQuery!!), 20).build()
        }
    }


    fun insertContactList(contacts: List<Contact>) {
        database.insertContactList(contacts)
    }
}