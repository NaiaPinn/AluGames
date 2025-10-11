package br.com.naiapinn.alugames.model

sealed class Plan(val type: String) {

    open fun obtainPrice(rent: Rent): Double {
        return rent.games.price * rent.period.inDays

    }
}