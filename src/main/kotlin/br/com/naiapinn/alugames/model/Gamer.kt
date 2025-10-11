package br.com.naiapinn.alugames.model

import java.util.*
import kotlin.random.Random

data class Gamer(var name: String, var email: String) {
    var birthDate: String? = null
    var userName: String? = null
        set(value) {
            field = value
            if (internalId.isNullOrBlank()) {
                generateInternalId()
            }
        }
    var internalId: String? = null
        private set
    var plan: Plan = IndividualPlan("BRONZE")
    val searchedGames = mutableListOf<Games?>()
    val rentedGames = mutableListOf<Rent>()

    constructor(name: String, email: String, birthDate: String, userName: String) :
            this(name, email) {
        this.name = name
        this.email = email
        generateInternalId()
    }

    init {
        this.email = verifyEmail()
        if (name.isBlank()) {
            throw IllegalArgumentException("Invalid name")
        }
    }

    override fun toString(): String {
        return "Gamer(name='$name', email='$email', birthDate=$birthDate, userName=$userName, internalId=$internalId)"
    }

    fun generateInternalId() {
        val number = Random.nextInt(10000)
        val tag = String.format("%05d", number)

        internalId = "$userName#$tag"
    }

    fun verifyEmail(): String {
        val regex = Regex(pattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        if (regex.matches(email)) {
            return email
        } else {
            throw IllegalArgumentException("Invalid E-mail")
        }
    }

    fun rentGame(games: Games, periodRent: PeriodRent): Rent {
        val rent = Rent(this, games, periodRent)
        rentedGames.add(rent)
        return rent
    }

    fun gameOfTheMonth(month:Int): List<Games> {
        return rentedGames
            .filter { rent ->  rent.period.startDate.monthValue == month}
            .map { rent ->  rent.games}
    }

    companion object {
        fun createGamer(reading: Scanner): Gamer {
            println("Welcome to AluGames! Let's make your registration. Enter your name:")
            val name = reading.nextLine()
            println("Enter your email:")
            val email = reading.nextLine()
            println("Do you want to complete your registration with user and date of birth? (Y/N)")
            val option = reading.nextLine()

            if (option.equals("s", true)) {
                println("Digite sua data de nascimento(DD/MM/AAAA):")
                val birthDate = reading.nextLine()
                println("Digite seu nome de usuário:")
                val userName = reading.nextLine()

                return Gamer(name, email, birthDate, userName)
            } else {
                return Gamer(name, email)
            }
        }
    }
}