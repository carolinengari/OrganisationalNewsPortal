package ngari.caroline.models

import java.util.*

data class Users(
        val name: String,
        val position: String,
        val staffRole: String,
        var id: Int = 0,
        ) {

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || this.javaClass != o.javaClass) return false
        val ( name1, position1, staffRole1, id1) = o as Users
        return  name == name1 && position == position1 && staffRole == staffRole1 && id ==id1
    }

    override fun hashCode(): Int {
        return Objects.hash(id, name, position, staffRole)
    }
}