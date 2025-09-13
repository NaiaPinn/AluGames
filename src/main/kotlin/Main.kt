package org.example

import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.Scanner
import kotlin.jvm.java

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

    val gson = Gson()
    val myInfoGames = gson.fromJson(json, InfoGame::class.java)
    val myGames = Games(myInfoGames.info.title, myInfoGames.info.thumb)
    println(myGames)
}
