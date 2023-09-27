package cl.daracenad.elearning.quiz.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.daracenad.elearning.quiz.data.database.dao.IAnswerDAO
import cl.daracenad.elearning.quiz.data.database.dao.ICourseDAO
import cl.daracenad.elearning.quiz.data.database.dao.IDownloadTableDAO
import cl.daracenad.elearning.quiz.data.database.dao.IMatriculateDAO
import cl.daracenad.elearning.quiz.data.database.dao.IParameterDAO
import cl.daracenad.elearning.quiz.data.database.dao.IQuestionDAO
import cl.daracenad.elearning.quiz.data.database.dao.ISchoolDAO
import cl.daracenad.elearning.quiz.data.database.dao.IStudentDAO
import cl.daracenad.elearning.quiz.data.database.dao.ITestTemplateDAO
import cl.daracenad.elearning.quiz.data.database.dao.ITopicDAO
import cl.daracenad.elearning.quiz.data.database.entities.AnswerEntity
import cl.daracenad.elearning.quiz.data.database.entities.DownloadTableEntity
import cl.daracenad.elearning.quiz.data.database.entities.AppParameterEntity
import cl.daracenad.elearning.quiz.data.database.entities.CourseEntity
import cl.daracenad.elearning.quiz.data.database.entities.MatriculateEntity
import cl.daracenad.elearning.quiz.data.database.entities.QuestionEntity
import cl.daracenad.elearning.quiz.data.database.entities.SchoolEntity
import cl.daracenad.elearning.quiz.data.database.entities.StudentEntity
import cl.daracenad.elearning.quiz.data.database.entities.TestTemplateEntity
import cl.daracenad.elearning.quiz.data.database.entities.TopicEntity
import cl.daracenad.elearning.quiz.domain.model.school.School
import cl.daracenad.elearning.quiz.utils.db.DataBaseResult
import cl.daracenad.elearning.quiz.utils.http.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryDB @Inject constructor(
    private val answerDAO:IAnswerDAO,
    private val courseDAO: ICourseDAO,
    private val testTemplateDAO:ITestTemplateDAO,
    private val topicDAO:ITopicDAO,

    private val questionDAO: IQuestionDAO,
    private val parameterDAO:IParameterDAO,

    private val downloadTableDAO: IDownloadTableDAO
    ){
    private val _userResponseLiveData = MutableLiveData<DataBaseResult<School>>()
    val userResponseLiveData: LiveData<DataBaseResult<School>>
        get() = _userResponseLiveData


    /**
     * Descargar de tablas
     */
    suspend fun downloadTablesInsertFromDB(entities:List<DownloadTableEntity>){
        downloadTableDAO.insertAll(entities)
    }

  //grabar tablas
    suspend fun answerInsertListFromDB(answerEntities: List<AnswerEntity>){
      answerDAO.insertAll(answerEntities)
    }
    suspend fun answerAllFromDB(): List<AnswerEntity>? {
        return answerDAO.all()
    }

    suspend fun courseInsertListFromDB(entities: List<CourseEntity>){
        courseDAO.insertAll(entities)
    }

    suspend fun testTemplateInsertListFromDB(entities: List<TestTemplateEntity>){
        testTemplateDAO.insertAll(entities)
    }

    suspend fun topicInsertListFromDB(entities: List<TopicEntity>){
        topicDAO.insertAll(entities)
    }

    fun topicFromDBAll(): Flow<List<TopicEntity>> {
        return topicDAO.all()
    }

    suspend fun questionInsertListFromDB(entities: List<QuestionEntity>){
        questionDAO.insertAll(entities)
    }

    suspend fun parameterFindKeyFromDB(key:String):AppParameterEntity{
        return parameterDAO.findForKey(key)
    }

    suspend fun parameterInsertFormDB(entity:AppParameterEntity) {
        return parameterDAO.insert(entity)
    }

    suspend fun parameterUpdateFormDB(entity:AppParameterEntity) {
        return parameterDAO.update(entity)
    }

    fun parameterAll(): Flow<List<AppParameterEntity>>{
        return parameterDAO.all()
    }


}