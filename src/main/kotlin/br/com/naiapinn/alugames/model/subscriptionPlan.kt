package br.com.naiapinn.alugames.model

class SubscriptionPlan(
    type: String,
    val monthlyFee: Double,
    val includedGames: Int): Plan(type) {

    override fun obtainPrice(rent: Rent): Double {
       val totalGamesPerMonth = rent.gamer.gameOfTheMonth(rent.period.startDate.monthValue).size+1
       return if (totalGamesPerMonth <= includedGames) {
           0.0
       } else {
           super.obtainPrice(rent)
       }

    }
}