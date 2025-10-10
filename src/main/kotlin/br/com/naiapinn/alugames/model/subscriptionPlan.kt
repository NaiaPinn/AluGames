package br.com.naiapinn.alugames.model

class subscriptionPlan(
    type: String,
    val monthlyFee: Double,
    val includedGames: Int): Plan(type) {
}