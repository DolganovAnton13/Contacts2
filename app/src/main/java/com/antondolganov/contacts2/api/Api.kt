package com.antondolganov.contacts2.api

import com.antondolganov.contacts2.data.model.Contact
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface Api {

    @GET("generated-01.json")
    fun sourceContactsOne(): Single<List<Contact>>

    @GET("generated-02.json")
    fun sourceContactsTwo(): Single<List<Contact>>

    @GET("generated-03.json")
    fun sourceContactsThree(): Single<List<Contact>>
}