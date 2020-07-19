package pl.dmcs

import javax.inject.Inject
import javax.inject.Singleton
import javax.ws.rs.core.Response

interface PersonService {
    fun fillData(data: List<Person>): Response
    fun delete(): Response
    fun getAll(): Response
}

@Singleton
private class PersonServiceImpl : PersonService {

    @Inject
    private lateinit var repository: PersonRepository

    override fun fillData(data: List<Person>): Response {
        return Response.ok(repository.fill(data)).build()
    }


    override fun delete(): Response {
        return Response.ok(repository.delete()).build()
    }

    override fun getAll(): Response {
        return Response.ok(repository.getAll()).build()
    }

}


