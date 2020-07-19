package pl.dmcs

import io.quarkus.runtime.annotations.RegisterForReflection
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


const val PATH_SLASH = "/"

@RegisterForReflection
@Path(PATH_SLASH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class PersonController {

    @Inject
    private lateinit var service: PersonService

    @POST
    @Path(PATH_SLASH)
    fun initData(data: List<Person>): Response {
        return service.fillData(data)
    }

    @GET
    @Path(PATH_SLASH)
    fun getPersonsMethod(): Response {
        return service.getAll()
    }

    @PUT
    @Path(PATH_SLASH)
    fun putMethod(data: List<Person>): Response {
        return service.fillData(data)
    }

    @DELETE
    @Path(PATH_SLASH)
    fun deleteMethod(): Response {
        return service.delete()
    }
}
