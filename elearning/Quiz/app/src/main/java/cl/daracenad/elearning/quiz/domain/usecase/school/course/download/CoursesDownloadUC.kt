package cl.daracenad.elearning.quiz.domain.usecase.school.course.download

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.daracenad.elearning.quiz.domain.model.school.Course
import cl.daracenad.elearning.quiz.domain.services.app.AppSchoolIdGetSrv
import cl.daracenad.elearning.quiz.domain.services.school.course.db.CoursesIntalledSrv
import cl.daracenad.elearning.quiz.utils.usercase.DTOResult
import javax.inject.Inject

class CoursesDownloadUC @Inject constructor(

    private val appSchoolIdGetSrv: AppSchoolIdGetSrv,
    private val topicDownloadUC: TopicDownloadUC,
    private val testTemplateDownloadUC: TestTemplateDownloadUC,
    private val questionDownloadUC: QuestionDownloadUC,
    private val answerDownloadUC: AnswerDownloadUC,
    private val coursesIntalledSrv: CoursesIntalledSrv
) {
    private val _dtoResultLD = MutableLiveData<DTOResult<String>>()
    val dtoResultLD: LiveData<DTOResult<String>>
        get() = _dtoResultLD

    suspend operator fun invoke(course:Course){

        var message = ""
        try{
            val schoolId = appSchoolIdGetSrv.invoke()

            //------------------------------------------------------------
            _dtoResultLD.postValue(DTOResult.Loading("Descargando Materias"))
            message = topicDownloadUC.invoke(schoolId, course.id)

            _dtoResultLD.postValue(DTOResult.Success(message))

            //------------------------------------------------------------
            _dtoResultLD.postValue(DTOResult.Loading("Descargando Plantillas de Test"))
            message = testTemplateDownloadUC.invoke(schoolId, course.id)
            _dtoResultLD.postValue(DTOResult.Success(message))

            _dtoResultLD.postValue(DTOResult.Loading("Descargando Preguntas de Test"))
            message = questionDownloadUC.invoke(schoolId, course.id)
            _dtoResultLD.postValue(DTOResult.Success(message))

            _dtoResultLD.postValue(DTOResult.Loading("Descargando Preguntas de Respuestas"))
            message = questionDownloadUC.invoke(schoolId, course.id)
            _dtoResultLD.postValue(DTOResult.Success(message))

            _dtoResultLD.postValue(DTOResult.Loading("Descargando Preguntas de Respuestas"))
            message = answerDownloadUC.invoke(schoolId, course.id)
            _dtoResultLD.postValue(DTOResult.Success(message))
            coursesIntalledSrv.invoke(course.id)





        }catch(ex:Exception){
            _dtoResultLD.postValue(DTOResult.Error("${ex.message}"))
        }
    }

}