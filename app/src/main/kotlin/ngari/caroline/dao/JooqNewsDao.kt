package ngari.caroline.dao

import ngari.caroline.models.DepartmentNews
import ngari.caroline.models.News
import ngari.caroline.util.DB
import org.jooq.DSLContext
import  ngari.caroline.jooq.tables.News.NEWS
import  ngari.caroline.jooq.tables.Departments.DEPARTMENTS
import ngari.caroline.jooq.tables.UsersDepartments.USERS_DEPARTMENTS


class JooqNewsDao() : NewsDao {
    private val context: DSLContext = DB.context

    override fun addNews(news: News?) {
        try {
            context.insertInto(NEWS,NEWS.NEWS_TYPE,NEWS.DEPARTMENT_ID, NEWS.USER_ID, NEWS.TITLE, NEWS.DESCRIPTION)
                    .values(news?.newsType,news?.departmentId,news?.userId,news?.title,news?.description)
                    .execute()
        } catch (e: Exception) {
            println(e)
        }
    }

    override fun addDepartmentNews(departmentnews: DepartmentNews?) {
        try {
            context.insertInto(NEWS,NEWS.NEWS_TYPE,NEWS.DEPARTMENT_ID, NEWS.USER_ID, NEWS.TITLE, NEWS.DESCRIPTION)
                    .values(departmentnews?.newsType,departmentnews?.departmentId,departmentnews?.userId,departmentnews?.title,departmentnews?.description)
                    .execute()

        } catch (e: Exception) {
            println(e)
        }
    }

    override val all: List<News>
        get() {
           return context.select(NEWS, NEWS.ID, NEWS.NEWS_TYPE, NEWS.TITLE, NEWS.DESCRIPTION, NEWS.USER_ID, NEWS.DEPARTMENT_ID)
                    .fetchInto(News::class.java)
        }

    override fun findById(id: Int): News? {
        return context.select(NEWS, NEWS.ID, NEWS.NEWS_TYPE, NEWS.TITLE, NEWS.DESCRIPTION, NEWS.USER_ID, NEWS.DEPARTMENT_ID)
                .where(NEWS.ID.eq(id))
                .fetchOneInto(News::class.java)
    }

    override fun clearAll() {
        try {
            context.deleteFrom(DEPARTMENTS).execute()
            context.deleteFrom(NEWS).execute()
            context.deleteFrom(USERS_DEPARTMENTS).execute()
        } catch (e: Exception) {
            println(e)
        }
    }
}