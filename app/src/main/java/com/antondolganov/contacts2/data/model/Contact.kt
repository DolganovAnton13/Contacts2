package com.antondolganov.contacts2.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.antondolganov.contacts2.data.Temperament
import java.text.SimpleDateFormat
import java.util.*


@Entity
data class Contact(
    @PrimaryKey
    val id: String,
    val name: String,
    val phone: String,
    var clearPhone: String,
    val height: Float,
    val biography: String,
    val temperament: Temperament,
    @Embedded val educationPeriod: EducationPeriod
) {
    fun getTemperamentName(): String {
        val name = temperament.name
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase()
    }

    fun getFormattedEducationPeriod(): String {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return if (educationPeriod.start.getTime() > educationPeriod.end.getTime())
            dateFormat.format(educationPeriod.end) + " - " + dateFormat.format(educationPeriod.start)
        else
            dateFormat.format(educationPeriod.start) + " - " + dateFormat.format(educationPeriod.end)
    }

    fun createClearPhone() {
       clearPhone= Regex("""\D+""").replace(phone, "")
    }
}