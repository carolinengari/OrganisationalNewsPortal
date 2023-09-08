package ngari.caroline.exceptions


class ApiException(val statusCode: Int, msg: String?) : RuntimeException(msg)