package br.com.naiapinn.alugames.data

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "games")
class GameEntity(
    val title: String? = "Title",
    val cover: String? = "Cover",
    val description: String = "",
    val price: Double = 0.0,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0) {
}