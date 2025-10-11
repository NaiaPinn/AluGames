package br.com.naiapinn.alugames.model

interface Recommended {
    val media: Double

    fun toRecommend(note: Int)
}