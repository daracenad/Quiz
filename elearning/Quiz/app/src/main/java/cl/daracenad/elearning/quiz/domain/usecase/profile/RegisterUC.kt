package cl.daracenad.elearning.quiz.domain.usecase.profile


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.daracenad.elearning.quiz.data.RepositorySchoolDB
import cl.daracenad.elearning.quiz.data.network.IUserAPI
import cl.daracenad.elearning.quiz.data.network.model.SchoolResponse
import cl.daracenad.elearning.quiz.domain.model.school.School
import cl.daracenad.elearning.quiz.domain.model.school.toDomain
import cl.daracenad.elearning.quiz.domain.services.app.AppEmailPutSrv
import cl.daracenad.elearning.quiz.domain.services.app.AppNamePutSrv
import cl.daracenad.elearning.quiz.domain.services.app.AppSchoolIdPutSrv
import cl.daracenad.elearning.quiz.domain.services.app.AppStudentIdPutSrv
import cl.daracenad.elearning.quiz.domain.services.app.AppTokenUpdateSrv
import cl.daracenad.elearning.quiz.utils.exception.APIDataNotSuccesFull
import cl.daracenad.elearning.quiz.utils.exception.APINotSuccesFull
import cl.daracenad.elearning.quiz.utils.usercase.DTOResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class RegisterUC@Inject constructor(
    private val api: IUserAPI,
    private val appEmailPutSrv: AppEmailPutSrv,
    private val appSchoolIdPutSrv: AppSchoolIdPutSrv,
    private val appStudentIdPutSrv: AppStudentIdPutSrv,
    private val appNamePutSrv: AppNamePutSrv,
    private val appTokenUpdateSrv: AppTokenUpdateSrv,
    private val repositorySchoolDB: RepositorySchoolDB
)
{
    private val _userResponseLiveData = MutableLiveData<DTOResult<School>>()
    val userResponseLiveData: LiveData<DTOResult<School>>
        get() = _userResponseLiveData

    suspend operator fun invoke(email:String, name: String, password: String, passwordConfirm:String) {
        _userResponseLiveData.postValue(DTOResult.Loading("hola"))

        val response = api.registerPOST(name,email,password, passwordConfirm)

        when (val rtnLogin = handleResponse(response)) {
            is DTOResult.Success -> {
                val school = rtnLogin.data
                val student = school?.student

                school?.let {
                    appTokenUpdateSrv(it.token!!)
                    appSchoolIdPutSrv.invoke(it.id!!)
                }
                student?.id?.let { appStudentIdPutSrv.invoke(it) }
                student?.id?.let { appEmailPutSrv.invoke(it) }
                student?.email?.let { appEmailPutSrv.invoke(it) }
                //Se debe registrar el estudiante
                if (student != null) {
                    try {
                        repositorySchoolDB.schoolInsertFromDB(school)
                        repositorySchoolDB.studentInsert(student)
                        _userResponseLiveData.postValue(rtnLogin)
                    }catch(ex:Exception){
                        Log.e("msgdad error db","${ex.message}")
                        _userResponseLiveData.postValue(
                            DTOResult.Error("DB : ${ex.message}")
                        )
                    }
                }
            }
            is DTOResult.Error -> {
                _userResponseLiveData.postValue(rtnLogin)
            }
            is DTOResult.Loading -> {

            }

            else -> {}
        }

    }

    private fun responseToSchool(response: Response<SchoolResponse>): School? {
        val school: School?

        if (response.isSuccessful) {
            val body = response.body()
            if (body?.success == 0) {
                school = body.school.let { it?.toDomain() }
                if (school != null) {
                    school.student = body.school?.student.let {
                        it?.toDomain(school.id)
                    }
                    if (body.school?.student?.matriculates != null) {
                        school.student?.matriculates =
                            body.school.student?.matriculates?.map { matriculateSerialize ->
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

