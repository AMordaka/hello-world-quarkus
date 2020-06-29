package pl.dmcs

import java.util.concurrent.ConcurrentHashMap
import javax.inject.Singleton

interface PersonRepository {
    fun update(id: Int, person: Person): Person
    fun insert(person: Person): Person
    fun findById(id: Int): Person?
    fun delete(id: Int): Boolean
    fun fill(data: List<Person>)
    fun getAll(): List<Person>
}


@Singleton
private class InMemoryPersonRepository : PersonRepository {

    private val store: MutableMap<Int, Person> = ConcurrentHashMap()

    override fun update(id: Int, person: Person): Person {
        store[id] = person
        return store[id]!!
    }

    override fun insert(person: Person): Person {
        val size = store.size
        store[size] = person
        return store[size]!!
    }

    override fun findById(id: Int): Person? {
        return store[id]
    }

    override fun delete(id: Int): Boolean {
        if (findById(id) == null) {
            return false
        }
        store.remove(id)
        return true
    }

    override fun fill(data: List<Person>) {
        data.forEachIndexed { index, person -> store[index] = person }
    }

    override fun getAll(): List<Person> {
        val list = mutableListOf<Person>()
        store.forEach { list.add(it.value) }
        return list
    }


}

data class Person(
        val firstName: String = "",
        val surname: String = "",
        val gender: String = "",
        val company: String = "",
        val email: String = "",
        val phone: String = "",
        val age: Int = 0,
        val country: String = "",
        val state: String = "",
        val city: String = ""
)


