package com.antondolganov.contacts2.repository

import android.annotation.SuppressLint
import com.antondolganov.contacts2.api.Api

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import com.antondolganov.contacts2.data.model.Contact
import io.reactivex.schedulers.Schedulers
import androidx.lifecycle.LiveData
import io.reactivex.Observable
import io.reactivex.Single


class DataRepository(var api: Api) {

    fun getContacts():Single<List<Contact>>{
        return Single.merge(api.sourceContactsOne(), api.sourceContactsTwo(), api.sourceContactsThree())
            .map {
                for (list in it) {
                    list.createClearPhone()
                }
                return@map it
            }
            .toList().map {
                var result = listOf<Contact>()
                for (list in it) {

                    result += list
                }
                return@map result
            }
    }
}