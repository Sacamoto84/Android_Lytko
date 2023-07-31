package com.example.lytko.json

import com.beust.klaxon.Klaxon
import com.example.lytko.device

data class update_chX(
    var type: String,
    var temp: Double,
    var time: Long,
    var relay: Int,
    var heating: String,
    var unit: String,
    var target_temp: Double,
    var newfw: String,
    var power_consumption: Int,
)

data class Update1ch(
var update_1ch : update_chX
)

data class Update2ch(
    var update_2ch : update_chX
)

//"""{"update_1ch":{
// "type":"thermostat",
// "temp":-237.6,
// "time":1663776785,
// "relay":1,
// "heating":"heat",
// "unit":"Celsius",
// "target_temp":28.5,
// "newfw":"0",
// "power_consumption":0}}"""

//Декодировка update_1chDecode
fun update1chJsonDecode(str: String = "", device : device) {
    println("JSON...update_1chDecode..$str")
    try {
        val result = Klaxon().parse<Update1ch>(str)
        device.type_1ch = result!!.update_1ch.type             // Тип thermostat
        device.temp_1ch = result.update_1ch.temp               // Текущая температура
        device.time_1ch = result.update_1ch.time               // Время наработки, ??? Нахуя
        device.relay_1ch = result.update_1ch.relay             // Реле в текущий момент
        device.heating_1ch = result.update_1ch.heating         // heat off ??? еще какае?
        device.unit_1ch = result.update_1ch.unit               // Celsius  ???
        device.target_temp_1ch = result.update_1ch.target_temp //Заданная температура
        device.newfw_1ch = result.update_1ch.newfw             // "0" Что такое неясно
        device.power_consumption_1ch = result.update_1ch.power_consumption // 0 Что такое
    }
    catch (e:Exception)
    {
        println("Error...update1chJsonDecode $e.message")
    }
}

//Декодировка update_2chDecode
fun update2chJsonDecode(str: String = "" , device : device) {
    println("JSON...update_2chDecode..$str")
    try {
        val result = Klaxon().parse<Update2ch>(str)
        device.type_2ch = result!!.update_2ch.type             // Тип thermostat
        device.temp_2ch = result.update_2ch.temp               // Текущая температура
        device.time_2ch = result.update_2ch.time               // Время наработки, ??? Нахуя
        device.relay_2ch = result.update_2ch.relay             // Реле в текущий момент
        device.heating_2ch = result.update_2ch.heating         // heat off ??? еще какае?
        device.unit_2ch = result.update_2ch.unit               // Celsius  ???
        device.target_temp_2ch = result.update_2ch.target_temp //Заданная температура
        device.newfw_2ch = result.update_2ch.newfw             // "0" Что такое неясно
        device.power_consumption_2ch = result.update_2ch.power_consumption // 0 Что такое
    }
    catch (e:Exception)
    {
        println("Error...update1chJsonDecode $e.message")
    }
}
