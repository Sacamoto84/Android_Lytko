package com.example.lytko.json

import com.beust.klaxon.Klaxon
import com.example.lytko.device

//{"wifi_networks":[{"ssid":"TP-Link_BC0C","chan":8,"signal":"58","encryption":"3"},{"ssid":"Sputnik 34","chan":11,"signal":"26","encryption":"3"},{"ssid":"m_2-26","chan":2,"signal":"8","encryption":"3"}]}
data class device_networks(
    var wifi_networks : Array<device_network> = arrayOf(device_network())
)


//Структура одной сети  //"ssid":"TP-Link_BC0C","chan":8,"signal":"58","encryption":"3"
data class device_network(
    var ssid       : String  = "", // "TP-Link_BC0C"
    var chan       : Int     = 0 , // 8
    var signal     : String  = "", // "58"
    var encryption : String  = "", // "3"
)


//Декодировка wifi_networksJsonDecode
fun wifi_networksJsonDecode(str: String = "", device : device) {
    println("JSON...wifi_networksDecode..$str")
    try {
        val result = Klaxon().parse<device_networks>(str)
        result!!.wifi_networks.forEach {
            device.device_networks.add(it)
        }
        println("")
    }
    catch (e:Exception)
    {
        println("Error...wifi_networksJsonDecode $e.message")
    }
}


