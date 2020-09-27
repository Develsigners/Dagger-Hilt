package `in`.gsrathoreniks.dagger_hilt.util

interface EntityMapper <Entity,DomainModel>{

//    interface to map Entity to Domain model

    fun mapFromEntity(entity: Entity): DomainModel

    fun mapToEntity(domainModel: DomainModel): Entity

}