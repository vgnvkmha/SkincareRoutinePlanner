package com.example.skincareroutineplanner.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.slf4j.event.LoggingEvent

object KtorClient {
    private val json = Json {
        ignoreUnknownKeys = true //игнорирует неизвестные поля в json (которых нет в data классе)
        isLenient = true   //разрешает более свободный синтаксис json
        explicitNulls = false
        coerceInputValues = true
    }
    val client: HttpClient by lazy {
        HttpClient(Android) {      //использует Android движок на основе OkHttp
            install(ContentNegotiation) {
                json(json)
            }
            install(HttpTimeout) { //плагин позволяет задать ограничения по времени для разных этапов HTTP-запроса.
                requestTimeoutMillis = 20000    //если весь процесс (подключение + ответ) занял слишком много времени
                connectTimeoutMillis = 10000    //если сервер вообще не отвечает
                socketTimeoutMillis = 10000     //Данные прекратили поступать слишком надолго
            }
        }
    }
}