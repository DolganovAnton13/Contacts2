package com.antondolganov.contacts2.callback

import com.antondolganov.contacts2.data.model.Contact

interface ContactClickListener {
    fun onContactClick(simpleContact: Contact)
}