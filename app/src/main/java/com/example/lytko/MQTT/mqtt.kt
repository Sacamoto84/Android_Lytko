package com.example.lytko.MQTT

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*


@SuppressLint("StaticFieldLeak")
private lateinit var mqttClient: MqttAndroidClient

private const val TAG = "MQTT"

fun MQTTconnect(context: Context) {

    val serverURI = "tcp://m2.wqtt.ru:10569"

    mqttClient = MqttAndroidClient(context, serverURI, "kotlin_client")

    mqttClient.setCallback(object : MqttCallback {

        //Получать новые сообщения от брокера
        override fun messageArrived(topic: String?, message: MqttMessage?) {
            Log.d(TAG, "Receive message: ${message.toString()} from topic: $topic")
        }

        //Потерял связь с брокером
        override fun connectionLost(cause: Throwable?) {
            Log.d(TAG, "Connection lost ${cause.toString()}")
        }

        //завершено доставка сообщения брокеру
        override fun deliveryComplete(token: IMqttDeliveryToken?) {

        }

    }
    )

    val options = MqttConnectOptions()
    options.userName = "u_GQD9V6"
    options.password = "nzZ8VtcL".toCharArray()

    try {
        mqttClient.connect(options, null, object : IMqttActionListener {
            override fun onSuccess(asyncActionToken: IMqttToken?) {
                Log.d(TAG, "Connection success")

                subscribe("climate/lytko/14559390/state")
                subscribe("climate/lytko/F0AA2412CFA4_2ch/state")
                subscribe("climate/lytko/1020431/state")

            }

            override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                Log.d(TAG, "Connection failure")
            }
        })
    } catch (e: MqttException) {
        e.printStackTrace()
    } catch (e: Exception) {
        println("MQTT Ошибка ${e.message}")
    }

}


fun subscribe(topic: String, qos: Int = 1) {
    try {
        mqttClient.subscribe(topic, qos, null, object : IMqttActionListener {

            override fun onSuccess(asyncActionToken: IMqttToken?) {
                Log.d(TAG, "Subscribed to $topic")
            }

            override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                Log.d(TAG, "Failed to subscribe $topic")
            }

        })
    } catch (e: MqttException) {
        e.printStackTrace()
    }
}

fun unsubscribe(topic: String) {
    try {
        mqttClient.unsubscribe(topic, null, object : IMqttActionListener {

            override fun onSuccess(asyncActionToken: IMqttToken?) {
                Log.d(TAG, "Unsubscribed to $topic")
            }

            override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                Log.d(TAG, "Failed to unsubscribe $topic")
            }

        })
    } catch (e: MqttException) {
        e.printStackTrace()
    }
}

fun publish(topic: String, msg: String, qos: Int = 1, retained: Boolean = false) {
    try {
        val message = MqttMessage()
        message.payload = msg.toByteArray()
        message.qos = qos
        message.isRetained = retained
        mqttClient.publish(topic, message, null, object : IMqttActionListener {
            override fun onSuccess(asyncActionToken: IMqttToken?) {
                Log.d(TAG, "$msg published to $topic")
            }

            override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                Log.d(TAG, "Failed to publish $msg to $topic")
            }
        })
    } catch (e: MqttException) {
        e.printStackTrace()
    }
}

fun disconnect() {
    try {
        mqttClient.disconnect(null, object : IMqttActionListener {

            override fun onSuccess(asyncActionToken: IMqttToken?) {
                Log.d(TAG, "Disconnected")
            }

            override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                Log.d(TAG, "Failed to disconnect")
            }

        })
    } catch (e: MqttException) {
        e.printStackTrace()
    }
}