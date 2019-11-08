package com.antondolganov.contacts2.repository

import com.antondolganov.contacts2.api.Api
import com.antondolganov.contacts2.viewmodel.ContactsListViewModel
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when`
import androidx.room.FtsOptions.Order
import com.antondolganov.contacts2.contactsListOne
import com.antondolganov.contacts2.contactsListResult
import com.antondolganov.contacts2.contactsListThree
import com.antondolganov.contacts2.contactsListTwo
import com.antondolganov.contacts2.data.model.Contact
import io.reactivex.Observable
import io.reactivex.Single
import org.hamcrest.CoreMatchers.`is`



class DataRepositoryTest {

    lateinit var dataRepository: DataRepository

    @Mock
    lateinit var api:Api

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)
        dataRepository = DataRepository(api)
    }

    @Test
    fun getContacts() {

        `when`(dataRepository.api.sourceContactsOne()).thenReturn(Single.just( contactsListOne))
        `when`(dataRepository.api.sourceContactsTwo()).thenReturn(Single.just( contactsListTwo))
        `when`(dataRepository.api.sourceContactsThree()).thenReturn(Single.just( contactsListThree))

        val list = dataRepository.getContacts().blockingGet()

        assertThat(list.size, `is`(contactsListResult.size))

        dataRepository.getContacts()
            .test()
            .assertValue{contacts->
                Observable.fromIterable(contacts)
                    .map { it.id }
                    .toList()
                    .blockingGet() == (contactsListResult).map { it.id }
            }

    }
}