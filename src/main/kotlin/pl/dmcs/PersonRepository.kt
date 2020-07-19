package pl.dmcs

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Singleton

interface PersonRepository {
    fun delete(): Boolean
    fun fill(data: List<Person>): List<Person>
    fun getAll(): List<Person>
}


@Singleton
class InMemoryPersonRepository : PersonRepository {

    private val store: MutableMap<Int, Person> = ConcurrentHashMap()

    override fun delete(): Boolean {
        store.forEach { store.remove(it.key) }
        return true
    }

    override fun fill(data: List<Person>): List<Person> {
        data.forEachIndexed { index, person -> store[index] = person }
        return data
    }

    override fun getAll(): List<Person> {
        val list = mutableListOf<Person>()
        store.forEach { list.add(it.value) }
        return list
    }


}

@JsonIgnoreProperties
data class Person(
        var firstName: String = "",
        var surname: String = "",
        var gender: String = "",
        var company: String = "",
        var email: String = "",
        var phone: String = "",
        var age: Int = 0,
        val address: Address = Address("Poland", "lodzkie", "Lodz"),
        val cars: List<Car> = listOf(
                Car("My audi", "123", CarType("Audi", "A1")),
                Car("BMW", "1234", CarType("BMW", "X1")),
                Car("Citroen", "12345", CarType("Citroen", "C3")),
                Car("Dacia", "123456", CarType("Dacia", "A1")),
                Car("My audi", "123", CarType("Audi", "A1")),
                Car("BMW", "1234", CarType("BMW", "X1")),
                Car("Citroen", "12345", CarType("Citroen", "C3")),
                Car("Dacia", "123456", CarType("Dacia", "A1")),
                Car("My audi", "123", CarType("Audi", "A1")),
                Car("BMW", "1234", CarType("BMW", "X1")),
                Car("Citroen", "12345", CarType("Citroen", "C3")),
                Car("Dacia", "123456", CarType("Dacia", "A1"))
        ),
        val test: Map<Int, Test> = mapOf(
                1 to Test(1, "test"),
                2 to Test(1, "test"),
                3 to Test(1, "test"),
                4 to Test(1, "test"),
                5 to Test(1, "test"),
                6 to Test(1, "test"),
                7 to Test(1, "test"),
                8 to Test(1, "test"),
                9 to Test(1, "test"),
                10 to Test(1, "test"),
                11 to Test(1, "test"),
                12 to Test(1, "test")
        )
)
@JsonIgnoreProperties
data class Address(
        val country: String,
        val state: String,
        val city: String
)

@JsonIgnoreProperties
data class Car(
        val name: String,
        val regNo: String,
        val type: CarType
)
@JsonIgnoreProperties
data class CarType(
        val mark: String,
        var model: String
)

@JsonIgnoreProperties
data class Test(
        val sum: Int,
        val name: String
)
