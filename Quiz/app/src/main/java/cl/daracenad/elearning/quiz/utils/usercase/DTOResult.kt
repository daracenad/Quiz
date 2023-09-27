package cl.daracenad.elearning.quiz.utils.usercase

sealed class DTOResult<T>(val data: T? = null, val message: String? = null) {

    class Success<T>(data: T) : DTOResult<T>(data)
    class Error<T>(message: String?, data: T? = null) : DTOResult<T>(data, message)
    class Loading<T> : DTOResult<T>()

}