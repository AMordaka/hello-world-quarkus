package pl.dmcs

import javax.inject.Inject
import javax.inject.Singleton
import javax.ws.rs.core.Response

interface PersonService {
    fun fillData(data: List<Person>)
    fun update(id: Int, person: Person): Response
    fun insert(person: Person): Response
    fun findById(id: Int): Response
    fun delete(id: Int): Response
    fun getAll(): Response
}

@Singleton
private class PersonServiceImpl : PersonService {

    @Inject
    private lateinit var repository: PersonRepository

    override fun fillData(data: List<Person>) {
        repository.fill(data)
    }

    override fun update(id: Int, person: Person): Response {
        return Response.ok(repository.update(id, person)).build()
    }

    override fun insert(person: Person): Response {
        return Response.ok(repository.insert(person)).build()
    }

    override fun findById(id: Int): Response {
        val person = repository.findById(id) ?: return Response.noContent().build()
        return Response.ok(person).build()
    }

    override fun delete(id: Int): Response {
        return Response.ok(repository.delete(id)).build()
    }

    override fun getAll(): Response {
        return Response.ok(repository.getAll()).build()
    }

}


