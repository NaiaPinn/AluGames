package br.com.naiapinn.alugames.services

import br.com.naiapinn.alugames.model.InfoGame
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ConsumerAPI {

    private val client: HttpClient = HttpClient.newHttpClient()
    private lateinit var request: HttpRequest
    private lateinit var response: HttpResponse<String>
    var json: String = ""
    private val gson = Gson()
    lateinit var myInfoGames: InfoGame

    fun searchGame(id: String): InfoGame? {
        val address = "https://www.cheapshark.com/api/1.0/games?id=$id"

         request = HttpRequest.newBuilder()
            .uri(URI.create(address))
            .build()

         response = client.send(request, HttpResponse.BodyHandlers.ofString())
         json = response.body()

        val jsonElement: JsonElement = JsonParser.parseString(json)

        return if (jsonElement.isJsonObject) {
            gson.fromJson(jsonElement, InfoGame::class.java)
        } else {
            null
        }
    }}