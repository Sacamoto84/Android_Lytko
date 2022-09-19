package com.example.lytko.screens.config

import com.example.lytko.R
import com.example.lytko.screens.Screen_Config_Current


val device0 : DeviceData = DeviceData()


fun init_demo_config() {

    device0.name = "Душевая"
    device0.nameChipId = "0x1234"

    device0.ScreenConfigList.add(StructScreenCurrentConfig(

        r = R.drawable.service_hysteresis,
        label = "Гистерезис",
        lastLabel = "3;-2.5",
        rLast = R.drawable.right

    ))

    device0.ScreenConfigList.add(StructScreenCurrentConfig(
        r = R.drawable.device_sensor,
        label = "Тип датчика",
        lastLabel = "dsa3235",
        rLast = R.drawable.right
    ))

    device0.ScreenConfigList.add(StructScreenCurrentConfig(
        r = R.drawable.device_sensor,
        label = "Внешние датчики",
        lastLabel = "Откл",
        rLast = R.drawable.right
    ))

    device0.ScreenConfigList.add(StructScreenCurrentConfig(
        r = R.drawable.wifi_signal3,
        label = "Wifi",
        lastLabel = "Network_1",
        rLast = R.drawable.right
    ))

    device0.ScreenConfigList.add(StructScreenCurrentConfig(
        r = R.drawable.service_server,
        label = "MQTT",
        lastLabel = "Подкл.",
        rLast = R.drawable.right
    ))

    device0.ScreenConfigList.add(StructScreenCurrentConfig(
        r = R.drawable.system_home,
        label = "Homekit",
        lastLabel = "",
        rLast = R.drawable.right
    ))

    device0.ScreenConfigList.add(StructScreenCurrentConfig(
        r = R.drawable.service_zigbee,
        label = "Zigbee",
        lastLabel = "",
        rLast = R.drawable.right
    ))

    device0.ScreenConfigList.add(StructScreenCurrentConfig(
        r = R.drawable.service_alisa,
        label = "Яндекс.Алиса",
        lastLabel = "Откл.",
        rLast = R.drawable.right
    ))

    device0.ScreenConfigList.add(StructScreenCurrentConfig(
        r = R.drawable.service_update,
        label = "Обновление",
        lastLabel = "Доступно",
        lastLabelLight = true,
        rLast = R.drawable.right
    ))

    device0.ScreenConfigList.add(StructScreenCurrentConfig(
        r = R.drawable.system_reset,
        label = "Сброс",
        lastLabel = "",
        rLast = R.drawable.right
    ))

    device0.ScreenConfigList.add(StructScreenCurrentConfig(
        r = R.drawable.service_network,
        label = "Настройка сети",
        lastLabel = "Только MQTT",
        rLast = R.drawable.right
    ))

}