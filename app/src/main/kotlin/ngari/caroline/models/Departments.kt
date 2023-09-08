package ngari.caroline.models

import java.util.*

data class Departments(
        val name: String,
        val description: String,
        val size: Int =0,
        val id: Int =0,
) {
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that: Departments = o as Departments
        return id == that.id && Objects.equals(name, that.name)
    }
}