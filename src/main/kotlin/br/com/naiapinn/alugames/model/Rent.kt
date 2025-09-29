package br.com.naiapinn.alugames.model

data class Rent(
    val gamer: Gamer,
    val games: Games){

    override fun toString(): String {
        return "Rented the game ${games.title} by ${gamer.name}"
    }
}
