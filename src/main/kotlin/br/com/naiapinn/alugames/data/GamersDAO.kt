package br.com.naiapinn.alugames.data

import br.com.naiapinn.alugames.model.Gamer
import javax.persistence.EntityManager

class GamersDAO(private val manager: EntityManager) {

    fun getGamers(): List<Gamer> {
        val query = manager.createQuery("FROM GamerEntity", GamerEntity::class.java)
        return query.resultList.map { entity -> Gamer(entity.name, entity.email, entity.birthDate, entity.user, entity.id) }
    }

    fun addGamer(gamer: Gamer) {
        val entity = GamerEntity(gamer.id, gamer.name, gamer.email, gamer.birthDate, gamer.userName)
        manager.transaction.begin()
        manager.persist(entity)
        manager.transaction.commit()
    }
}