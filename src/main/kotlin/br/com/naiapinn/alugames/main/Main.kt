package br.com.naiapinn.alugames.main

import br.com.naiapinn.alugames.model.Gamer
import br.com.naiapinn.alugames.model.Games
import br.com.naiapinn.alugames.services.ConsumerAPI
import transformAge
import java.util.*

fun main() {
    val reading = Scanner(System.`in`)
    val gamer = Gamer.createGamer(reading)
    println("Registration successfully completed. Gamer Data:")
    println(gamer)
    println("Gamer age: " + gamer.birthDate?.transformAge())

    do {
        println("Enter a game code to search: ")
        val search = reading.nextLine()
        val consumerAPI = ConsumerAPI()
        val infoSearchGame = consumerAPI.searchGame(search)


        var myGame: Games? = null
        var result = runCatching{}


        if (infoSearchGame != null) {
            result = runCatching {
                myGame = Games(infoSearchGame.info.title, infoSearchGame.info.thumb)
                result.onSuccess {
                    println("Do you want to enter a personalized description? Y/N")
                    val option = reading.nextLine()

                    if (option.equals("Y", true)){
                        println("Enter custom description for the game:")
                        val description = reading.nextLine()
                        myGame?.description = description
                    } else {
                        myGame?.description = myGame.title.toString()
                    }
                    gamer.searchedGames.add(myGame)
                }
            }
        } else {
            println("Invalid ID. Please try again.")
        }
        result.onFailure {
            println("Error searching for the game. Please try again.")
        }
        println("Want to search for a new game? Y/N")
        val response = reading.nextLine()

    } while (response.equals("Y", ignoreCase = true))

    println("Searched games:")
    println(gamer.searchedGames)

    println("Title sorted games: ")
    gamer.searchedGames.sortBy {
        it?.title
    }

    gamer.searchedGames.forEach {
        println("Title: " + it?.title)
    }

    val filteredGames  = gamer.searchedGames.filter {
        it?.title?.contains("batman", true) ?: false
    }
    println("Filtered Games: ")
    println(filteredGames)

    println("Want to delete some game from the original list? (Y/N)")
    val option = reading.nextLine()
    if (option.equals("y", true)){
        println(gamer.searchedGames)
        println("Enter the position of the game you want to delete: ")
        val position = reading.nextInt()
        gamer.searchedGames.removeAt(position)
    }
    println("Updated list: ")
    println(gamer.searchedGames)
    println("Search successfully finished")

}
