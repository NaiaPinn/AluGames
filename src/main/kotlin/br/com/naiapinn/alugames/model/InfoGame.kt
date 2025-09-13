package br.com.naiapinn.alugames.model

data class InfoGame(val info: InfoApiShark) {
    override fun toString(): String {
        return info.toString()
    }
}