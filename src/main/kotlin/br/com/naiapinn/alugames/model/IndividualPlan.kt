package br.com.naiapinn.alugames.model

class IndividualPlan(type: String,  id: Int = 0) : Plan(type, id) {

    override fun obtainPrice(rent: Rent): Double {
        var originalValue = super.obtainPrice(rent)
        if (rent.gamer.media > 8){
            originalValue -= originalValue * 0.1
        }
        return originalValue
    }

    override fun toString(): String {
        return "Individual Plan\n" +
                "Type: $type\n" +
                "Id: $id\n"
    }
}
