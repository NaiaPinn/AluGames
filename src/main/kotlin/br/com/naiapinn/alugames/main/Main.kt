package br.com.naiapinn.alugames.main

import br.com.naiapinn.alugames.model.Games
import br.com.naiapinn.alugames.model.InfoGame
import com.google.gson.Gson
import com.google.gson.JsonParser
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.*

fun main() {
    val reading = Scanner(System.`in`)
    println("Enter a game code to search: ")
    val search = reading.nextLine()
    val address = "https://www.cheapshark.com/api/1.0/games?id=$search"

    val client: HttpClient = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
        .uri(URI.create(address))
        .build()

    val response = client
        .send(request, HttpResponse.BodyHandlers.ofString())
    val json = response.body()
    var myInfoGames: InfoGame? = null
    val gson = Gson()
    val jsonElement = JsonParser.parseString(json)
    if (jsonElement.isJsonObject){
        myInfoGames = gson.fromJson(json, InfoGame::class.java)
    } else if (jsonElement.isJsonArray){
        println("Invalid id. Try again")
        return
    }

    var myGame: Games? = null
    val result = runCatching {
        if (myInfoGames != null){
            myGame = Games(myInfoGames.info.title, myInfoGames.info.thumb)
        }
    }

    result.onFailure {
        println("Error searching for the game. Please try again.")
    }

    result.onSuccess {
        if (myInfoGames != null){
            println("Do you want to enter a personalized description? S/N")
            val option = reading.nextLine()

            if (option.equals("S", true)){
                println("Enter custom description for the game:")
                val description = reading.nextLine()
                myGame?.description = description
            } else {
                myGame?.description = myGame.title
            }
            println(myGame)
        }
    }
}
