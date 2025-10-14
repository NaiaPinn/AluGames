package br.com.naiapinn.alugames.data

import br.com.naiapinn.alugames.model.Games
import javax.persistence.EntityManager

class GamesDAO(override val manager: EntityManager): DAO<Games, GameEntity>(manager, GameEntity::class.java) {

    override fun toEntity(obj: Games): GameEntity {
        return GameEntity(obj.title, obj.cover, obj.description, obj.price, obj.id)
    }

    override  fun getList(): List<Games> {
        val query = manager.createQuery("FROM GameEntity", GameEntity::class.java)
        val resultList = query.resultList as List<GameEntity>
        return resultList.map { entity ->
            Games(entity.title, entity.cover, entity.description, entity.price, entity.id)

        }
    }

    override fun toModel(entity: GameEntity): Games {
       return Games(entity.title, entity.cover, entity.description, entity.price, entity.id)
    }
}