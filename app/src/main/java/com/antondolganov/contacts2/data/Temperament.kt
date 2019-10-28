package com.antondolganov.contacts2.data

import com.google.gson.annotations.SerializedName

enum class Temperament {

    @SerializedName("melancholic") MELANCHOLIC,
    @SerializedName("phlegmatic") PHLEGMATIC,
    @SerializedName("sanguine") SANGIUNE,
    @SerializedName("choleric") CHOLERIC
}