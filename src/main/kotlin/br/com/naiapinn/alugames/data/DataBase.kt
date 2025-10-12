package br.com.naiapinn.alugames.data

import br.com.naiapinn.alugames.model.Games
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object DataBase {

    fun getConnection(): Connection? {
        return try {
            DriverManager.getConnection("jdbc:mysql://localhost:3306/alugames", "your user", "your password")
        } catch (e: SQLException) {
            e.printStackTrace()
            null
        }
    }

    fun getGames(): List<Games> {
        val listGames = mutableListOf<Games>()
        val connection = getConnection()
        if (connection != null){
            try {
                val statement = connection.createStatement()
                val result = statement.executeQuery("SELECT * FROM GAMES")
                while (result.next()) {
                    val id = result.getInt("id")
                    val title = result.getString("title")
                    val cover =  result.getString("cover")
                    val description =  result.getString("description")
                    val price = result.getDouble("price")
                    val game = Games(title, cover, description, price, id)
                    listGames.add(game)
                }
                statement.close()
            } finally {
                connection.close()
            }
        }
        return listGames
    }
}