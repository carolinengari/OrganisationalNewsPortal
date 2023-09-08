package ngari.caroline.dao

import ngari.caroline.models.Departments
import ngari.caroline.models.News
import ngari.caroline.models.Users
import ngari.caroline.util.DB
import org.jooq.DSLContext
import  ngari.caroline.jooq.tables.News.NEWS
import  ngari.caroline.jooq.tables.Departments.DEPARTMENTS
import ngari.caroline.jooq.tables.Staff.STAFF
import ngari.caroline.jooq.tables.UsersDepartments.USERS_DEPARTMENTS
import java.lang.Exception

class JooqDepartmentsDao() : DepartmentsDao {
    private val context: DSLContext = DB.context

    override fun add(department: Departments?) {
        try {
            context.insertInto(DEPARTMENTS, DEPARTMENTS.NAME, DEPARTMENTS.DESCRIPTION, DEPARTMENTS.SIZE)
                    .values(department?.name,department?.description,department?.size)
                    .execute()
        } catch (e: Exception) {
            println(e)
        }
    }

    override fun addUserToDepartment(user: Users?, department: Departments?) {
        try {
            context.insertInto(USERS_DEPARTMENTS, USERS_DEPARTMENTS.USER_ID, USERS_DEPARTMENTS.DEPARTMENT_ID)
                    .values(user?.id, department?.id)
                    .execute()
            val departmentSize = context.selectCount()
                    .from(USERS_DEPARTMENTS)
                    .where(USERS_DEPARTMENTS.DEPARTMENT_ID.eq(department?.id))
                    .execute()
            context.update(DEPARTMENTS)
                    .set(DEPARTMENTS.SIZE,departmentSize)
                    .where(DEPARTMENTS.ID.eq(department?.id))
                    .execute()
        } catch (e: Exception) {
            println(e)
        }
    }

    override val all: List<Departments>
        get() {
            return context.select(DEPARTMENTS, DEPARTMENTS.ID, DEPARTMENTS.NAME, DEPARTMENTS.DESCRIPTION, DEPARTMENTS.SIZE)
                    .fetchInto(Departments::class.java)
        }

    override fun findById(id: Int): Departments? {
        return context.select(DEPARTMENTS, DEPARTMENTS.ID, DEPARTMENTS.NAME, DEPARTMENTS.DESCRIPTION, DEPARTMENTS.SIZE)
                .where(DEPARTMENTS.ID.eq(id))
                .fetchOneInto(Departments::class.java)

    }

    override fun getAllUsersInDepartment(departmentId: Int): List<Users?> {
        return context.select(STAFF.ID, STAFF.NAME, STAFF.POSITION, STAFF.STAFF_ROLE)
                .from(STAFF)
                .join(USERS_DEPARTMENTS)
                .on(STAFF.ID.eq(USERS_DEPARTMENTS.USER_ID))
                .where(USERS_DEPARTMENTS.DEPARTMENT_ID.eq(departmentId))
                .fetchInto(Users::class.java)
    }

    override fun getDepartmentNews(id: Int): List<News?> {
        return context.select(NEWS, NEWS.ID, NEWS.NEWS_TYPE, NEWS.TITLE, NEWS.DESCRIPTION, NEWS.USER_ID, NEWS.DEPARTMENT_ID)
                .where(NEWS.ID.eq(id))
                .fetchInto(News::class.java)
    }

    override fun clearAll() {
        try {
            context.deleteFrom(DEPARTMENTS).execute()
            context.deleteFrom(USERS_DEPARTMENTS).execute()
        } catch (e: Exception) {
            println(e)
        }
    }
}