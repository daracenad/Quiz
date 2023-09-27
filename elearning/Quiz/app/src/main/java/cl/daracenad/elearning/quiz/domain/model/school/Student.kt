package cl.daracenad.elearning.quiz.domain.model.school
import cl.daracenad.elearning.quiz.data.database.entities.StudentEntity
import cl.daracenad.elearning.quiz.data.network.model.serialize.StudentSerialize

data class Student(
    val id:String,
    val schoolId:String,
    val name:String,
    val email:String,
    var matriculates:List<Matriculate>?
)

fun StudentEntity.toDomain() = Student(id =id, name =name, email = email, schoolId = schoolId,
    matriculates = null
)
fun Student.toEntity() = StudentEntity(id=id,name=name, email = email, schoolId = schoolId)
fun StudentSerialize.toDomain(schoolId:String) = Student(id =id, name =name, email = email, schoolId = schoolId,
    matriculates = null
)
