package com.example.lytko.json

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Klaxon
import com.example.lytko.device


const val dataUpdate1 =
    """{"update_1ch":{"type":"thermostat","temp":-237.6,"time":1663776785,"relay":1,"heating":"heat","unit":"Celsius","target_temp":28.5,"newfw":"0","power_consumption":0}}"""

val dataConfig: String =
    """{"config":{"set":"1","pair_hk":0,"mqtt_server":"","mqtt_port":"","mqtt_login":"","mqtt_use":"0","mqtt_alice":"0","wifi_name":"TP-Link_BC0C","lock_mode":0,"model":"103","time_zone":"+3.0","name":"LYTKO-F0AA2412CFA4","dwin_link":"stub","dwin_version_new":"01.01.19","version":"03.01.53","link":"","version_new":"","dwin_version":"01.01.19"}}"""

val dataNetWork: String =
    """{"wifi_networks":[{"ssid":"TP-Link_BC0C","chan":8,"signal":"58","encryption":"3"},{"ssid":"Sputnik 34","chan":11,"signal":"26","encryption":"3"},{"ssid":"m_2-26","chan":2,"signal":"8","encryption":"3"}]}"""


fun jsonDecode(str: String = "", device : device) {
    val data = str
    //MARK: Определяем тип Json по первому ключу
    val X = data.substringAfter('"').substringBefore('"')
    when (X)
    { // подстрока до первого указанного разделителя
        "update_1ch"    -> update1chJsonDecode(data, device)
        "update_2ch"    -> update2chJsonDecode(data, device)
        "wifi_networks" -> wifi_networksJsonDecode(data , device)
    }

}
















