package br.com.naiapinn.alugames.data

import javax.persistence.EntityManager

@Suppress("UNCHECKED_CAST")
abstract class DAO <TModel,TEntity> (protected open val manager: EntityManager, protected val entityType:  Class<TEntity>){

    abstract fun toEntity(obj: TModel): TEntity
    abstract fun toModel(entity: TEntity): TModel
    open fun getList(): List<TModel>{
        val query = manager.createQuery("FROM ${entityType.simpleName}", entityType )
        val resultList = query.resultList as List<*>
        return resultList.map { entity ->
            toModel(entity as TEntity)
        }
    }

    open fun add(obj: TModel){
        val entity = toEntity(obj)
        manager.transaction.begin()
        manager.persist(entity)
        manager.transaction.commit()
    }

    open fun recoverById(id: Int): TModel{
        val query = manager.createQuery("FROM ${entityType.simpleName} WHERE id=:id", entityType )
        query.setParameter("id", id)
        val entity = query.singleResult
        return toModel(entity)
    }

    open fun delete(id: Int){
        val query = manager.createQuery("FROM ${entityType.simpleName} WHERE id=:id", entityType )
        query.setParameter("id", id)
        val entity = query.singleResult

        manager.transaction.begin()
        manager.remove(entity)
        manager.transaction.commit()
    }
}