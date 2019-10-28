package com.antondolganov.contacts2.api

import com.antondolganov.contacts2.data.model.Contact
import io.reactivex.Observable
import retrofit2.http.GET

interface Api {

    @GET("generated-01.json")
    fun sourceContactsOne(): Observable<List<Contact>>

    @GET("generated-02.json")
    fun sourceContactsTwo(): Observable<List<Contact>>

    @GET("generated-03.json")
    fun sourceContactsThree(): Observable<List<Contact>>
}