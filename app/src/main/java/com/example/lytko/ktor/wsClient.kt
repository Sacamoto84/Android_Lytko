package com.example.lytko.ktor

import com.example.lytko.device0
import com.example.lytko.json.jsonDecode
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.websocket.*
import kotlinx.coroutines.*
import libs.ping3
import java.time.Duration

val client = HttpClient(OkHttp) {
    install(WebSockets){
        pingInterval = 3000
        //pingPeriod = Duration.ofSeconds(60) // Disabled (null) by default
        //timeout = Duration.ofSeconds(15)
    }

    install(HttpTimeout) {
        requestTimeoutMillis = 3000
    }

    install(ContentNegotiation) {
        json()
    }
}

var isClientKeepAlive = false //Признак того стомы соеденились

fun ktor_rutine() {
    isClientKeepAlive = false
    val scope = CoroutineScope(Dispatchers.IO)
    scope.launch {
        while (true) {
            delay(3000)
            println("!!!!....ищем ESP32")
            try {
                if (ping3("192.168.0.200"))
                {
                    println("!!!!....Нашли esp32")
                    client.ws(
                        method = HttpMethod.Get,
                        host = "192.168.0.200",
                        port = 80, path = "/ws"
                    ) {
                        println("KTOR___Подключились")
                        isClientKeepAlive = true
                        //val messageOutputRoutine = launch { outputMessages() }
                        val userInputRoutine = launch { inputMessages() }
                        val pinging = launch {
                            while (true) {
                                delay(10000)
                                print("Пингуем")
                                try {
                                    if (!ping3("192.168.0.200")) {

                                        println("\nKTOR___pinging___Нет ответа")
                                        isClientKeepAlive = false
                                        userInputRoutine.cancel()
                                        break
                                    }
                                    else
                                        println("..OK")
                                } catch (e: Exception) {
                                    println("KTOR___pinging___Exception: " + e.localizedMessage)
                                    isClientKeepAlive = false
                                    break
                                }
                            }
                        }
                        //val othersMessage = incoming.receive() as? Frame.Text ?: break
                        //println(othersMessage.readText())
                        userInputRoutine.join() // Ожидаем завершения корутины Wait for completion; either "exit" or error
                        //messageOutputRoutine.cancelAndJoin()
                        println("!!!!....userInputRoutine.join()")
                        cancel()
                        close(CloseReason(CloseReason.Codes.CANNOT_ACCEPT, "Client said BYE"))
                        println("!!!!....cancel() close")
                    }
                }
                else
                    println("!!!!....Нету esp32")
            }
            catch (e: Exception)
            {
                println(">Exception WebSocket ${e.message}")
            }
        }
    }
    println("KTOR.............Connection closed. Goodbye!")
}

suspend fun DefaultClientWebSocketSession.outputMessages() {
    try {
        for (message in incoming) {
            message as? Frame.Text ?: continue
            println(message.readText())
        }
    } catch (e: Exception) {
        println("KTOR.............Error while receiving: " + e.localizedMessage)
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun DefaultClientWebSocketSession.inputMessages() {
    //val othersMessage = incoming.receive() as? Frame.Text ?: break
    //println(othersMessage.readText())
    while (true) {

        if (!isClientKeepAlive) {
            println("Выход по isClientKeepAlive == false из inputMessages")
            cancel()
            break
        }

        if (!incoming.isEmpty) {
            val othersMessage = incoming.receive() as? Frame.Text ?: break
            val str = othersMessage.readText()
            println(str)
            jsonDecode(str, device0)

        }

        //val message = readLine() ?: ""
        //if (message.equals("exit", true)) return
        //try {
       //     send(message)
        //} catch (e: Exception) {
        //    println("KTOR.............Error while sending: " + e.localizedMessage)
        //    return
       // }
    }
}