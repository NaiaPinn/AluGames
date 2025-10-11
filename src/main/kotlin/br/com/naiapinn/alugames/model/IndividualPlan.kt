package br.com.naiapinn.alugames.model

class IndividualPlan(type: String) : Plan(type) {

    override fun obtainPrice(rent: Rent): Double {
        var originalValue = super.obtainPrice(rent)
        if (rent.gamer.media > 8){
            originalValue -= originalValue * 0.1
        }
        return originalValue
    }
}
