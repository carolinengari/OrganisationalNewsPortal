package ngari.caroline.models

import java.util.*

data class DepartmentNews (
        val userId: Int,
        val title: String,
        val description: String,
        val newsType: String,
        val departmentId: Int,
        val NEWSTYPE: String = "department",
        val id: Int = 0,
){

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val news = o as News
        return id == news.id &&
                departmentId == news.departmentId &&
                userId == news.userId &&
                newsType == news.newsType &&
                title == news.title &&
                description == news.description &&
                NEWSTYPE == news.NEWSTYPE
    }

    override fun hashCode(): Int {
        return Objects.hash(id, newsType, departmentId, userId, title, description, NEWSTYPE)
    }
}