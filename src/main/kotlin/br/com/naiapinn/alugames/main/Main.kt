package br.com.naiapinn.alugames.main

import br.com.naiapinn.alugames.model.Games
import br.com.naiapinn.alugames.services.ConsumerAPI
import com.google.gson.Gson
import java.util.*

fun main() {
    val reading = Scanner(System.`in`)
    println("Enter a game code to search: ")
    val search = reading.nextLine()
    val gson = Gson()
    val consumerAPI = ConsumerAPI()
    val infoSearchGame = consumerAPI.searchGame(search)


    var myGame: Games? = null
    var result = runCatching{}


    if (infoSearchGame != null) {
         result = runCatching {
            myGame = Games(infoSearchGame.info.title, infoSearchGame.info.thumb)
             result.onSuccess {
                 println("Do you want to enter a personalized description? S/N")
                 val option = reading.nextLine()

                 if (option.equals("S", true)){
                     println("Enter custom description for the game:")
                     val description = reading.nextLine()
                     myGame?.description = description
                 } else {
                     myGame?.description = myGame.title.toString()
                 }
                 println(myGame)
             }
        }
    } else {
        println("Invalid ID. Please try again.")
    }
    result.onFailure {
        println("Error searching for the game. Please try again.")
    }
}
