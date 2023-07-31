package com.example.lytko

import com.example.lytko.json.device_network
import com.example.lytko.json.device_networks

//Огромнная структура определяет всю информацию по устройству
data class device(

    var name : String   = "", // "LYTKO-F0AA2412CFA4"

    //Config
    var id: String      = "", //F0AA2412CFA4
    var ip: String      = "", //192.168.0.200
    var type: String    = "", //esp32_panel_4inch
    var type1ch: String = "", //thermostat    >type_1ch
    var type2ch: String = "", //thermostat    >type_1ch

    ////////////////////////////////////////////////////////////////////
    var type_1ch              : String = "",  // Тип thermostat ????
    var temp_1ch              : Double = 0.0, // Текущая температура
    var time_1ch              : Long   = 0,   // Время наработки, ??? Нахуя
    var relay_1ch             : Int    = 0,   // Реле в текущий момент
    var heating_1ch           : String = "",  // heat off ??? еще какае?
    var unit_1ch              : String = "",  // Celsius  ???
    var target_temp_1ch       : Double = 0.0, //Заданная температура
    var newfw_1ch             : String = "",  // "0" Что такое неясно
    var power_consumption_1ch : Int    = 0,   // 0 Что такое
    ////////////////////////////////////////////////////////////////////
    var type_2ch              : String = "",  // Тип thermostat ????
    var temp_2ch              : Double = 0.0, // Текущая температура
    var time_2ch              : Long   = 0,   // Время наработки, ??? Нахуя
    var relay_2ch             : Int    = 0,   // Реле в текущий момент
    var heating_2ch           : String = "",  // heat off ??? еще какае?
    var unit_2ch              : String = "",  // Celsius  ???
    var target_temp_2ch       : Double = 0.0, //Заданная температура
    var newfw_2ch             : String = "",  // "0" Что такое неясно
    var power_consumption_2ch : Int    = 0,   // 0 Что такое
    ////////////////////////////////////////////////////////////////////


    //wifi_networks
    var device_networks: MutableList<device_network> = mutableListOf<device_network>()


)


var device0 :device = device()




