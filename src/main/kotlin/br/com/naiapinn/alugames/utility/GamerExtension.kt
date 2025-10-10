package br.com.naiapinn.alugames.utility

import br.com.naiapinn.alugames.model.Gamer
import br.com.naiapinn.alugames.model.InfoGamerJson

fun InfoGamerJson.createGamer(): Gamer {
    return Gamer(this.name, this.email, this.birthDate, this.userName)
}