package com.antondolganov.contacts2.repository

import android.annotation.SuppressLint
import com.antondolganov.contacts2.api.Api

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import com.antondolganov.contacts2.data.model.Contact
import io.reactivex.schedulers.Schedulers
import androidx.lifecycle.LiveData
import io.reactivex.Observable


class DataRepository(var api: Api) {
    private val result: MutableLiveData<List<Contact>> = MutableLiveData()
    private val status: MutableLiveData<String> = MutableLiveData()
    private var isUpdateInProgress: Boolean = false

    @SuppressLint("CheckResult")
    fun getContacts(): LiveData<List<Contact>> {
        isUpdateInProgress = true

        Observable.merge(api.sourceContactsOne(), api.sourceContactsTwo(), api.sourceContactsThree())
            .subscribeOn(Schedulers.io())
            .flatMap { it -> Observable.fromIterable(it) }
            .doOnNext(Contact::createClearPhone)
            .toList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ contacts ->
                result.setValue(contacts)
                isUpdateInProgress = false
                status.setValue("Загружено")
            },
                { throwable ->
                    isUpdateInProgress = false
                    status.setValue("Ошибка загрузки: "+ throwable.message)
                })

        return result
    }

    fun isUpdateInProgress(): Boolean {
        return isUpdateInProgress
    }
}