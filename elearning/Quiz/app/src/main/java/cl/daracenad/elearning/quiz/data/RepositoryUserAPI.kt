package cl.daracenad.elearning.quiz.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.daracenad.elearning.quiz.data.network.IUserAPI
import cl.daracenad.elearning.quiz.utils.exception.APIDataNotSuccesFull
import cl.daracenad.elearning.quiz.utils.exception.APINotSuccesFull
import cl.daracenad.elearning.quiz.data.network.model.SchoolResponse
import cl.daracenad.elearning.quiz.data.network.model.school.EnrolledCoursePost
import cl.daracenad.elearning.quiz.domain.model.school.School
import cl.daracenad.elearning.quiz.domain.model.school.toDomain
import cl.daracenad.elearning.quiz.utils.http.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class RepositoryUserAPI  @Inject constructor(
    private val api: IUserAPI
) {
    private val _userResponseLiveData = MutableLiveData<NetworkResult<School>>()
    val userResponseLiveData: LiveData<NetworkResult<School>>
        get() = _userResponseLiveData
    //ocupados
    //private val userDAO:IUserDAO
    suspend fun loginFromAPI(user:String, password:String){//: School? {

        _userResponseLiveData.postValue(NetworkResult.Loading())


        val response =  api.loginPOST(user, password)


        handleResponse(response)
        //return api.loginFromAPI(user, password)?.let { responseToSchool(it) }
    }
    suspend fun profileGetFromAPI(token: String,studentId:String){//: School? {

        val response =  api.userProfileGET(token,studentId)
        handleResponse(response)
        //return api.profileGET(token,studentId)?.let { responseToSchool(it) }
    }
    suspend fun registerFromAPI(name: String, email:String, password:String, password_confirmation:String){//: School? {
        val response =  api.registerPOST(name,email, password, password_confirmation)
        handleResponse(response)
        //return api.register(name,email, password, password_confirmation)?.let { responseToSchool(it) }
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