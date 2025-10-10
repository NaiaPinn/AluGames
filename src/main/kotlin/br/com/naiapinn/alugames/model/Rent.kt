package br.com.naiapinn.alugames.model


data class Rent(
    val gamer: Gamer,
    val games: Games,
    val period: PeriodRent
) {

    val rentPrice = gamer.plan.obtainPrice(this)

    override fun toString(): String {
        return "Rented the game ${games.title} by ${gamer.name} with the price ${rentPrice}"
    }
}
