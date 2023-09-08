package ngari.caroline

import com.google.gson.Gson
import ngari.caroline.models.News
import ngari.caroline.dao.JooqDepartmentsDao
import ngari.caroline.dao.JooqNewsDao
import ngari.caroline.dao.JooqUsersDao
import ngari.caroline.exceptions.ApiException
import ngari.caroline.models.Departments
import ngari.caroline.models.Users

import spark.Filter
import spark.Request
import spark.Response
import spark.Spark


object App {
    private val port: Int
        get() {
            val processBuilder = ProcessBuilder()
            return if (processBuilder.environment()["PORT"] != null) {
                processBuilder.environment()["PORT"]!!.toInt()
            } else 4567
            //return default port if heroku-port isn't set (i.e. on localhost)
        }

    @JvmStatic
    fun main(args: Array<String>) {
        Spark.port(port)
        val gson: Gson = Gson()
        Spark.staticFileLocation("/public")

        val jooqDepartmentsDao = JooqDepartmentsDao()
        val jooqNewsDao = JooqNewsDao()
        val jooqUsersDao = JooqUsersDao()

        //adding a new user
        Spark.post("/users/new", "application/json") { request: Request, response: Response ->  //tested..............
            val user: Users = gson.fromJson<Users>(request.body(), Users::class.java)
            jooqUsersDao.add(user)
            response.status(201)
            gson.toJson(user)
        }
        Spark.post("/departments/new", "application/json") { request: Request, response: Response ->  //tesdted................
            val departments: Departments = gson.fromJson(request.body(), Departments::class.java)
            jooqDepartmentsDao.add(departments)
            response.status(201)
            gson.toJson(departments)
        }
        //adding users to a specific department
        Spark.post("/add/user/:user_id/department/:department_id", "application/json") { request: Request, response: Response ->  //tested......
            val user_id = request.params("user_id").toInt()
            val department_id = request.params("department_id").toInt()
            val departments = jooqDepartmentsDao.findById(department_id)
            val users = jooqUsersDao.findById(user_id)
            if (departments == null) {
                throw ApiException(404, String.format("No department with the id: \"%s\" exists",
                        request.params("department_id")))
            }
            if (users == null) {
                throw ApiException(404, String.format("No user with the id: \"%s\" exists",
                        request.params("user_id")))
            }
            jooqDepartmentsDao.addUserToDepartment(users, departments)
            val departmentUsers = jooqDepartmentsDao.getAllUsersInDepartment(departments.id)
            response.status(201)
            gson.toJson(departmentUsers)
        }
        //adding general news in the department

//        post("/news/new/general","application/json",(request, response) -> {//tested................
//
//            News news =gson.fromJson(request.body(),News.class);
//            sql2oNewsDao.addNews(news);
//            response.status(201);
//            return gson.toJson(news);
//        });
        Spark.post("/news/new/department", "application/json") { request: Request, response: Response ->  //tested.......
            val department_news: News = gson.fromJson(request.body(), News::class.java)
            val departments = jooqDepartmentsDao.findById(department_news.departmentId)
            val users = jooqUsersDao.findById(department_news.userId)
            if (departments == null) {
                throw ApiException(404, String.format("No department with the id: \"%s\" exists",
                        request.params("id")))
            }
            if (users == null) {
                throw ApiException(404, String.format("No user with the id: \"%s\" exists",
                        request.params("id")))
            }
            jooqNewsDao.addNews(department_news)
            response.status(201)
            gson.toJson(department_news)
        }
        Spark.post("/news/new/general", "application/json") { request: Request, response: Response ->  //tested
            val news: News = gson.fromJson(request.body(), News::class.java)
            jooqNewsDao.addNews(news)
            response.status(201)
            gson.toJson(news)
        }


        //getting users in the department
        Spark.get("/users", "application/json") { request: Request?, response: Response? ->  //tested
            if (jooqDepartmentsDao.all.size > 0) {
                return@get gson.toJson(jooqUsersDao.all)
            } else {
                return@get "{\"RESPONSE\":\"NO USERS CURRENTLY\"}"
            }
        }

        //path to show departments
        Spark.get("/departments", "application/json") { request: Request?, response: Response? ->  //tested.............
            if (jooqDepartmentsDao.all.size > 0) {
                return@get gson.toJson(jooqDepartmentsDao.all)
            } else {
                return@get "{\"RESPONSE\":\"NO DEPARTMENTS CURRENTLY\"}"
            }
        }
        //path to get listed general news
        Spark.get("/news/general", "application/json") { request: Request?, response: Response? ->  //tested.....works!!
            if (jooqNewsDao.all.size > 0) {
                return@get gson.toJson(jooqNewsDao.all)
            } else {
                return@get "{\"RESPONSE\":\"NO NEWS AVAILABLE\"}"
            }
        }
        //        //path to get each user department
        Spark.get("/user/:id/departments", "application/json") { request: Request, response: Response? ->  //tested ......works
            val id = request.params("id").toInt()
            if (jooqUsersDao.getAllUserDepartments(id).size > 0) {
                return@get gson.toJson(jooqUsersDao.getAllUserDepartments(id))
            } else {
                return@get "{\"RESPONSE\":\"DEPARTMENT NOT AVAILABLE\"}"
            }
        }
        //getting specific user..................
        Spark.get("/user/:id", "application/json") { request: Request, response: Response? ->  //tested and works...........
            val id = request.params("id").toInt()
            if (jooqUsersDao.findById(id) == null) {
                throw ApiException(404, String.format("No user with the id: \"%s\" exists",
                        request.params("id")))
            } else {
                return@get gson.toJson(jooqUsersDao.findById(id))
            }
        }
        Spark.get("/department/:id/users", "application/json") { request: Request, response: Response? ->  //tested works............
            val id = request.params("id").toInt()
            if (jooqDepartmentsDao.getAllUsersInDepartment(id).size > 0) {
                return@get gson.toJson(jooqDepartmentsDao.getAllUsersInDepartment(id))
            } else {
                return@get "{\"message\":\"I'm sorry, but department has no users.\"}"
            }
        }
        Spark.get("/department/:id", "application/json") { request: Request, response: Response? ->  //works.......
            val id = request.params("id").toInt()
            if (jooqDepartmentsDao.findById(id) == null) {
                throw ApiException(404, String.format("No department with the id: %s exists",
                        request.params("id")))
            } else {
                return@get gson.toJson(jooqDepartmentsDao.findById(id))
            }
        }
        Spark.get("/news/department/:id", "application/json") { request: Request, response: Response? ->  //works
            val id = request.params("id").toInt()
            val (name, description, id1, size) = jooqDepartmentsDao.findById(id)
                    ?: throw ApiException(404, String.format("No department with the id: \"%s\" exists",
                            request.params("id")))
            if (jooqDepartmentsDao.getDepartmentNews(id).size > 0) {
                return@get gson.toJson(jooqDepartmentsDao.getDepartmentNews(id))
            } else {
                return@get "{\"RESPONSE\":\"THIS DEPARTMENT HAS NO NEWS CURRENTLY\"}"
            }
        }


        //FILTERS
        Spark.exception(ApiException::class.java) { exception: ApiException, request: Request?, response: Response ->
            val jsonMap: MutableMap<String, Any?> = HashMap()
            jsonMap["status"] = exception.statusCode
            jsonMap["errorMessage"] = exception.message
            response.type("application/json")
            response.status(exception.statusCode)
            response.body(gson.toJson(jsonMap))
        }
        Spark.after(Filter { request: Request?, response: Response -> response.type("application/json") })
    }
}