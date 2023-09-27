package cl.daracenad.elearning.quiz.domain.model

class User (
    val id:Int,
    val name:String,
    val eMail:String,
    val schoolId:String,
    val token:String
){
    companion object{
        const val NAME:String = "name"
        const val EMAIL:String = "email"
        const val STUDENT_ID:String = "student_id"
        const val TOKEN:String = "token"
        const val SCHOOL_ID = "school_id"
        const val MATRICULATE = "matriculate"
        const val PASSWORD = "password"

    }
}