package com.tolib.charges.data.repository

import com.tolib.charges.data.model.ChargeItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChargeRepository(private val api: ApiService) {
    suspend fun getCharges(): List<ChargeItem> = withContext(Dispatchers.IO){
        api.getCharges()
    }
}