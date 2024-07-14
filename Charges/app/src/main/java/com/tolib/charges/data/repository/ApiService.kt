package com.tolib.charges.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tolib.charges.data.model.ChargeItem
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

interface ApiService {
    suspend fun getCharges(): List<ChargeItem> {
        delay(TimeUnit.SECONDS.toMillis(1))
        val response =
            """[{"city":"Moscow","charger":{"busy":true,"name":"Энергия Москвы","address":"Измайловское ш., 4А"}},{"city":"Moscow","charger":{"busy":false,"name":"Lipgart","address":"2-я Бауманская ул., 5, стр. 5"}},{"city":"Saint Petersburg","charger":{"busy":true,"name":"Станция зарядки электромобилей","address":"Большой просп. Васильевского острова, 68"}},{"city":"Moscow","charger":{"busy":false,"name":"Zevs","address":"Мясницкая ул., 13, стр. 10"}},{"city":"Saint Petersburg","charger":{"busy":false,"name":"Punkt E","address":"Малая Монетная ул., 2Г"}}]"""
        val listPersonType = object : TypeToken<List<ChargeItem>>() {}.type
        return Gson().fromJson(response, listPersonType)
    }
}