package ngari.caroline.dao

import ngari.caroline.models.DepartmentNews
import ngari.caroline.models.News


interface NewsDao {
    //create
    fun addNews(news: News?)
    fun addDepartmentNews(departmentnews: DepartmentNews?)

    //read
    val all: List<News?>?

    fun findById(id: Int): News?

    //update
    //delete
    fun clearAll()
}