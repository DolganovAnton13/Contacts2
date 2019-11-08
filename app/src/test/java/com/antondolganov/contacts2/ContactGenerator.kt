package com.antondolganov.contacts2

import com.antondolganov.contacts2.data.Temperament
import com.antondolganov.contacts2.data.model.Contact
import com.antondolganov.contacts2.data.model.EducationPeriod
import java.util.*


val contactsListOne = listOf(
    Contact(
        id = "0",
        name = "name0",
        phone = "+000000111",
        height = 123f,
        clearPhone = "000000111",
        biography = "bio",
        temperament = Temperament.MELANCHOLIC,
        educationPeriod = EducationPeriod(Date(),Date())
    )
)

val contactsListTwo = listOf(

    Contact(
        id = "1",
        name = "name1",
        phone = "+111111",
        height = 123f,
        biography = "bio",
        clearPhone = "000000111",
        temperament = Temperament.PHLEGMATIC,
        educationPeriod = EducationPeriod(Date(),Date())
    )
)

val contactsListThree = listOf(
    Contact(
        id = "2",
        name = "name2",
        phone = "+222222333",
        height = 123f,
        clearPhone = "000000111",
        biography = "bio",
        temperament = Temperament.CHOLERIC,
        educationPeriod = EducationPeriod(Date(),Date())
    ),
    Contact(
        id = "3",
        name = "name3",
        phone = "+333333",
        height = 123f,
        clearPhone = "000000111",
        biography = "bio",
        temperament = Temperament.PHLEGMATIC,
        educationPeriod = EducationPeriod(Date(),Date())
    ),
    Contact(
        id = "4",
        name = "name4",
        phone = "+444444111",
        height = 123f,
        clearPhone = "000000111",
        biography = "bio",
        temperament = Temperament.SANGIUNE,
        educationPeriod = EducationPeriod(Date(),Date())
    )
)

val contactsListResult = listOf(
    Contact(
        id = "0",
        name = "name0",
        phone = "+000000111",
        height = 123f,
        clearPhone = "000000111",
        biography = "bio",
        temperament = Temperament.MELANCHOLIC,
        educationPeriod = EducationPeriod(Date(),Date())
    ),
    Contact(
        id = "1",
        name = "name1",
        phone = "+111111",
        height = 123f,
        biography = "bio",
        clearPhone = "000000111",
        temperament = Temperament.PHLEGMATIC,
        educationPeriod = EducationPeriod(Date(),Date())
    ),
    Contact(
        id = "2",
        name = "name2",
        phone = "+222222333",
        height = 123f,
        clearPhone = "000000111",
        biography = "bio",
        temperament = Temperament.CHOLERIC,
        educationPeriod = EducationPeriod(Date(),Date())
    ),
    Contact(
        id = "3",
        name = "name3",
        phone = "+333333",
        height = 123f,
        clearPhone = "000000111",
        biography = "bio",
        temperament = Temperament.PHLEGMATIC,
        educationPeriod = EducationPeriod(Date(),Date())
    ),
    Contact(
        id = "4",
        name = "name4",
        phone = "+444444111",
        height = 123f,
        clearPhone = "000000111",
        biography = "bio",
        temperament = Temperament.SANGIUNE,
        educationPeriod = EducationPeriod(Date(),Date())
    )
)