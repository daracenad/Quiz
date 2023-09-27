package cl.daracenad.elearning.quiz.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.daracenad.elearning.quiz.data.network.ISchoolAPI

import cl.daracenad.elearning.quiz.data.network.model.SchoolResponse
import cl.daracenad.elearning.quiz.data.network.model.school.EnrolledCoursePost
import cl.daracenad.elearning.quiz.domain.model.school.School
import cl.daracenad.elearning.quiz.domain.model.school.toDomain
import cl.daracenad.elearning.quiz.utils.exception.APIDataNotSuccesFull
import cl.daracenad.elearning.quiz.utils.exception.APINotSuccesFull
import cl.daracenad.elearning.quiz.utils.http.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class RepositorySchoolAPI  @Inject constructor(
    private val api: ISchoolAPI
) {
    private val _userResponseLiveData = MutableLiveData<NetworkResult<School>>()
    val userResponseLiveData: LiveData<NetworkResult<School>>
        get() = _userResponseLiveData
    //ocupados

    suspend fun coursesToEnrollAPI(token: String,studentId:String, ids:List<String>){//: School? {
        val response =  api.matriculate(token, EnrolledCoursePost(studentId, ids))
        handleResponse(response)
        //return api.coursesToEnroll(token,studentId,ids)?.let { responseToSchool(it) }
    }
    private fun responseToSchool(response: Response<SchoolResponse>): School? {
        var school: School?=null

        if(response?.isSuccessful == true){
            val body = response.body()
            if(body?.success == 0){
                school =  body.school.let { it?.toDomain() }
                if (school != null) {
                    school.student = body.school?.student.let {
                        it?.toDomain(school.id)
                    }
                    if(body.school?.student?.matriculates!= null){
                        school?.student?.matriculates  = body.school?.student?.matriculates?.map { matriculateSerialize->
                            matriculateSerialize.toDomain(
                                matriculateSerialize.enrolledCourses?.map {enrolledCourseSerialize ->
                                    enrolledCourseSerialize.toDomain(
                                        enrolledCourseSerialize.courses?.map { courseSerialize ->
                                            courseSerialize.toDomain("PD")
                                        }
                                    )
                                }
                            )
                        }
                    }

                    if(body.school != null){
                        school.courses = body.school?.courses?.map {
                            it.toDomain("PD")
                        }!!
                    }
                }
            }else{
                throw APIDataNotSuccesFull("${body?.messages?.get(0)}")
            }
        }else{
            throw APINotSuccesFull("Registro: No se pudo registrar intente más tarde ${response?.message()}")
        }
        return school

    }

    private fun handleResponse(response: Response<SchoolResponse>?) {
        if (response != null) {
            if (response.isSuccessful && response?.body() != null) {
                if(response.body()!!.success != 0){
                    _userResponseLiveData.postValue(
                        NetworkResult.Error("${response.body()!!.messages?.get(0)}")
                    )
                }else {
                    _userResponseLiveData.postValue(
                        NetworkResult.Success(responseToSchool(response)!!)
                    )
                }
            }
            else if(response.errorBody()!=null){
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                _userResponseLiveData.postValue(
                    NetworkResult.Error(errorObj.getString("message"))
                )
            }
            else{
                _userResponseLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
            }
        }
    }

}