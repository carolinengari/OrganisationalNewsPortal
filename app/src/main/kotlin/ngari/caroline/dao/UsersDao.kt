package ngari.caroline.dao

import ngari.caroline.models.Departments
import ngari.caroline.models.Users


interface UsersDao {
    //create
    fun add(user: Users?)

    //read
    val all: List<Users?>?

    fun getAllUserDepartments(userId: Int): List<Departments?>?
    fun findById(id: Int): Users?

    //update
    //delete
    fun clearAll()
}