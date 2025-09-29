package br.com.naiapinn.alugames.utility

import br.com.naiapinn.alugames.model.Games
import br.com.naiapinn.alugames.model.InfoGameJson


fun InfoGameJson.createGame(): Games {
    return Games(this.title, this.cover, this.description, this.price)
}