package cl.daracenad.elearning.quiz.domain


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.daracenad.elearning.quiz.data.network.IUserAPI
import cl.daracenad.elearning.quiz.data.network.model.SchoolResponse
import cl.daracenad.elearning.quiz.domain.model.school.School
import cl.daracenad.elearning.quiz.domain.model.school.toDomain
import cl.daracenad.elearning.quiz.domain.services.course.app.AppStudentIdGetSrv
import cl.daracenad.elearning.quiz.domain.services.course.app.AppTokenGetSrv
import cl.daracenad.elearning.quiz.utils.exception.APIDataNotSuccesFull
import cl.daracenad.elearning.quiz.utils.exception.APINotSuccesFull
import cl.daracenad.elearning.quiz.utils.usercase.DTOResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class UserProfileUC @Inject constructor(
    private val userTokenGetUC: AppTokenGetSrv,
    private val userStudentIdGetUC: AppStudentIdGetSrv,
    private val api: IUserAPI
) {
    private val _dtoResultLD = MutableLiveData<DTOResult<School>>()
    val dtoResultLD: LiveData<DTOResult<School>>
        get() = _dtoResultLD

    suspend operator fun invoke(){
        _dtoResultLD.postValue(DTOResult.Loading())
        val token = userTokenGetUC.invoke()
        val studentId = userStudentIdGetUC.invoke()

        val response = api.userProfileGET("Bearer $token",studentId)
        val dtoResult = handleResponse(response)

        when (dtoResult) {
            is DTOResult.Success -> {
                _dtoResultLD.postValue(dtoResult)
            }
            is DTOResult.Error -> {
                _dtoResultLD.postValue(dtoResult)
            }
            is DTOResult.Loading -> {

            }
        }
    }

    private fun responseToSchool(response: Response<SchoolResponse>): School? {
        var school: School? = null

        if (response?.isSuccessful == true) {
            val body = response.body()

            if (body?.success == 0) {
                school = body.school.let { it?.toDomain() }
                if (school != null) {
                    school.student = body.school?.student.let {
                        it?.toDomain(school.id)
                    }
                    if (body.school?.student?.matriculates != null) {
                        school?.student?.matriculates =
                            body.school?.student?.matriculates?.map { matriculateSerialize ->
                                matriculateSerialize.toDomain(
                                    matriculateSerialize.enrolledCourses?.map { enrolledCourseSerialize ->
                                        enrolledCourseSerialize.toDomain(
                                            enrolledCourseSerialize.courses?.map { courseSerialize ->
                                                courseSerialize.toDomain("PD")
                                            }
                                        )
                                    }
                                )
                            }
                    }

                    if (body.school != null) {
                        school.courses = body.school?.courses?.map {
                            it.toDomain("PD")
                        }!!
                    }
                }
            } else {
                throw APIDataNotSuccesFull("${body?.messages?.get(0)}")
            }
        } else {
            throw APINotSuccesFull("Registro: No se pudo registrar intente más tarde ${response?.message()}")
        }
        return school

    }

    private fun handleResponse(response: Response<SchoolResponse>?): DTOResult<School> {
        if (response != null) {
            if (response.isSuccessful && response?.body() != null) {

                if (response.body()!!.success != 0) {
                    //_userResponseLiveData.postValue(
                    return DTOResult.Error("${response.body()!!.messages?.get(0)}")
                    //)
                } else {
                    //_userResponseLiveData.postValue(
                    return DTOResult.Success(responseToSchool(response)!!)
                    //)
                }
            } else if (response.errorBody() != null) {

                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                //_userResponseLiveData.postValue(
                return DTOResult.Error(errorObj.getString("message"))
                //)
            } else {
                //_userResponseLiveData.postValue(
                return DTOResult.Error("Something Went Wrong")
                //)
            }
        }
        return DTOResult.Error("Sin retorno de información desde el servidor")
    }

}