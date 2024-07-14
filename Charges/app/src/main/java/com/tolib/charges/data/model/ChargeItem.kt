package com.tolib.charges.data.model

import com.google.gson.annotations.SerializedName

data class ChargeItem(
    @SerializedName("city")
    val city: String,
    @SerializedName("charger")
    val charger: Charger
)
