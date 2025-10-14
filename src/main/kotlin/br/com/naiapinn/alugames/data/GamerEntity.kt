package br.com.naiapinn.alugames.data

import javax.persistence.*

@Entity
@Table(name = "gamers")
class GamerEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    val name: String = "Name",
    val email: String = "email@email.com",
    val birthDate:String? = null,
    val user:String? = null) {
}