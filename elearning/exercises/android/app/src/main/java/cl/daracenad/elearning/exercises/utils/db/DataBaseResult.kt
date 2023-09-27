package cl.daracenad.elearning.exercises.utils.db

sealed class DataBaseResult<T>(val data: T? = null, val message: String? = null) {

    class Success<T>(data: T) : DataBaseResult<T>(data)
    class Error<T>(message: String?, data: T? = null) : DataBaseResult<T>(data, message)
    class Loading<T> : DataBaseResult<T>()

}