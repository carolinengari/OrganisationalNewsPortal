package ngari.caroline.dao

import ngari.caroline.models.Departments
import ngari.caroline.models.News
import ngari.caroline.models.Users

interface DepartmentsDao {
    //create
    fun add(department: Departments?)
    fun addUserToDepartment(user: Users?, department: Departments?)

    //read
    val all: List<Any?>?

    fun findById(id: Int): Departments?
    fun getAllUsersInDepartment(departmentId: Int): List<Users?>
    fun getDepartmentNews(id: Int): List<News?>?

    //update
    //delete
    fun clearAll()
}