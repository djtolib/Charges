package com.tolib.charges.data.model
import com.google.gson.annotations.SerializedName
data class Charger(
    @SerializedName("busy")
    val busy: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("address")
    val address: String
)
