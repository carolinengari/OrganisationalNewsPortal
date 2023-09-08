package ngari.caroline.util

import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import java.sql.DriverManager

class DB {
    private var url: String = "jdbc:sqlite:db/news.db"

    companion object {
        lateinit var context: DSLContext
    }

    init {
        DriverManager.getConnection(url).use {
            conn -> context = DSL.using(conn, SQLDialect.SQLITE)
        }
    }

}