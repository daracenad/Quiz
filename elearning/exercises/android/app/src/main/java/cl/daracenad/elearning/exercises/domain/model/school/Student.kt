package cl.daracenad.elearning.exercises.domain.model.school
import cl.daracenad.elearning.exercises.data.database.entities.StudentEntity
import cl.daracenad.elearning.exercises.data.network.model.serialize.StudentSerialize

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
