package ngari.caroline.dao

import ngari.caroline.jooq.tables.Staff.STAFF
import ngari.caroline.jooq.tables.UsersDepartments.USERS_DEPARTMENTS
import ngari.caroline.jooq.tables.Departments.DEPARTMENTS


import ngari.caroline.models.Departments
import ngari.caroline.models.Users
import ngari.caroline.util.DB
import org.jooq.DSLContext


class JooqUsersDao() : UsersDao {
    private val context: DSLContext = DB.context

    override fun add(user: Users?) {
        try {
            context.insertInto(STAFF, STAFF.NAME, STAFF.POSITION, STAFF.STAFF_ROLE)
                    .values(user?.name, user?.position, user?.staffRole)
                    .execute()
        } catch (e: Exception) {
            println(e)
        }
    }

    override val all: List<Users>
        get() {
            return context.select(STAFF, STAFF.ID, STAFF.NAME, STAFF.POSITION, STAFF.STAFF_ROLE)
                    .fetchInto(Users::class.java)
        }

    override fun getAllUserDepartments(userId: Int): List<Departments?> {
        return context.select(DEPARTMENTS.ID, DEPARTMENTS.NAME, DEPARTMENTS.DESCRIPTION, DEPARTMENTS.SIZE)
                .from(DEPARTMENTS)
                .join(USERS_DEPARTMENTS)
                .on(USERS_DEPARTMENTS.DEPARTMENT_ID.eq(DEPARTMENTS.ID))
                .fetchInto(Departments::class.java)
    }

    override fun findById(id: Int): Users? {
        return context.select(STAFF, STAFF.ID, STAFF.NAME, STAFF.POSITION, STAFF.STAFF_ROLE)
                .from(STAFF)
                .where(STAFF.ID.eq(id))
                .fetchOneInto(Users::class.java)

    }

    override fun clearAll() {
        context.deleteFrom(STAFF).execute()
        context.deleteFrom(USERS_DEPARTMENTS).execute()
    }
}