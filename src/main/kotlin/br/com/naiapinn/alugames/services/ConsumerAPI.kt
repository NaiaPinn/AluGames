package br.com.naiapinn.alugames.services

import br.com.naiapinn.alugames.model.Gamer
import br.com.naiapinn.alugames.model.Games
import br.com.naiapinn.alugames.model.InfoGame
import br.com.naiapinn.alugames.model.InfoGameJson
import br.com.naiapinn.alugames.model.InfoGamerJson
import br.com.naiapinn.alugames.utility.createGame
import br.com.naiapinn.alugames.utility.createGamer
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ConsumerAPI {

    private val client: HttpClient = HttpClient.newHttpClient()
    private lateinit var request: HttpRequest
    private lateinit var response: HttpResponse<String>
    private val gson = Gson()

    private fun consumerData(address: String): String {
        request = HttpRequest.newBuilder()
            .uri(URI.create(address))
            .build()

        response = client.send(request, HttpResponse.BodyHandlers.ofString())
       return response.body()

    }

    fun searchGame(id: String): InfoGame? {
        val address = "https://www.cheapshark.com/api/1.0/games?id=$id"
        val json = consumerData(address)
        val jsonElement: JsonElement = JsonParser.parseString(json)

        return if (jsonElement.isJsonObject) {
            gson.fromJson(jsonElement, InfoGame::class.java)
        } else {
            null
        }
    }

    fun searchGameJson(): List<Games> {
        val address = "https://raw.githubusercontent.com/NaiaPinn/AluGames/refs/heads/alugames/json/games.json"
        val json = consumerData(address)
        val gson = Gson()
        val myGamesType = object  : TypeToken<List<InfoGameJson>>() {}.type
        val listGames: List<InfoGameJson> = gson.fromJson(json, myGamesType)

        val listaGamesMap = listGames.map { infoGameJson -> infoGameJson.createGame() }

        return listaGamesMap
    }

    fun searchGamer(): List<Gamer> {
        val address = "https://raw.githubusercontent.com/NaiaPinn/AluGames/refs/heads/alugames/json/gamers.json"
        val json = consumerData(address)
        val gson = Gson()
        val myGamerType = object  : TypeToken<List<InfoGamerJson>>() {}.type
        val listGamer: List<InfoGamerJson> = gson.fromJson(json, myGamerType)

        val listaGamerMap = listGamer.map { infoGamerJson -> infoGamerJson.createGamer() }

        return listaGamerMap
    }
}