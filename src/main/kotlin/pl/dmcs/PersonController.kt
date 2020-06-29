package pl.dmcs

import io.quarkus.runtime.annotations.RegisterForReflection
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


const val PATH_SLASH = "/"
const val PATH_ID = "{id}"
const val PATH_INIT = "init"

@RegisterForReflection
@Path(PATH_SLASH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class PersonController {

    @Inject
    private lateinit var service: PersonService

    @POST
    @Path(PATH_INIT)
    fun initData(data: List<Person>) {
        return service.fillData(data)
    }

    @GET
    @Path(PATH_ID)
    fun getSinglePersonMethod(@PathParam("id") id: Int): Response {
        return service.findById(id)
    }

    @GET
    fun getPersonsMethod(): Response {
        return service.getAll()
    }

    @POST
    fun postMethod(person: Person): Response {
        return service.insert(person)
    }

    @PUT
    @Path(PATH_ID)
    fun putMethod(@PathParam("id") id: Int, person: Person): Response {
        return service.update(id, person)
    }

    @DELETE
    @Path(PATH_ID)
    fun deleteMethod(@PathParam("id") id: Int): Response {
        return service.delete(id)
    }
}