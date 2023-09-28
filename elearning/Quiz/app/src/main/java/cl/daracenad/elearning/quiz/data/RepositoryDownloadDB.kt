package cl.daracenad.elearning.quiz.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.daracenad.elearning.quiz.data.database.dao.IAnswerDAO
import cl.daracenad.elearning.quiz.data.database.dao.IAppParameterDAO
import cl.daracenad.elearning.quiz.data.database.dao.ICourseDAO
import cl.daracenad.elearning.quiz.data.database.dao.IDownloadTableDAO
import cl.daracenad.elearning.quiz.data.database.dao.ILocalParameterDAO
import cl.daracenad.elearning.quiz.data.database.dao.IQuestionDAO
import cl.daracenad.elearning.quiz.data.database.dao.ITestTemplateDAO
import cl.daracenad.elearning.quiz.data.database.dao.ITopicDAO
import cl.daracenad.elearning.quiz.data.database.entities.AnswerEntity
import cl.daracenad.elearning.quiz.data.database.entities.AppParameterEntity
import cl.daracenad.elearning.quiz.data.database.entities.DownloadTableEntity
import cl.daracenad.elearning.quiz.data.database.entities.LocalParameterEntity
import cl.daracenad.elearning.quiz.data.database.entities.CourseEntity
import cl.daracenad.elearning.quiz.data.database.entities.QuestionEntity
import cl.daracenad.elearning.quiz.data.database.entities.TestTemplateEntity
import cl.daracenad.elearning.quiz.data.database.entities.TopicEntity
import cl.daracenad.elearning.quiz.domain.model.school.School
import cl.daracenad.elearning.quiz.utils.db.DataBaseResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryDownloadDB @Inject constructor(
    private val answerDAO:IAnswerDAO,
    private val courseDAO: ICourseDAO,
    private val testTemplateDAO:ITestTemplateDAO,
    private val topicDAO:ITopicDAO,
    private val appParameterDAO: IAppParameterDAO,
    private val questionDAO: IQuestionDAO,
    private val localParameterDAO:ILocalParameterDAO,
    private val parameterDAO: IAppParameterDAO,
    private val downloadTableDAO: IDownloadTableDAO
    ){
    private val _userResponseLiveData = MutableLiveData<DataBaseResult<School>>()
    val userResponseLiveData: LiveData<DataBaseResult<School>>
        get() = _userResponseLiveData

/*
    fun findForKey(schoolId:String, courseId:String): AppParameterEntity{
        return appParameterDAO.findForKey(schoolId, courseId)
    }
    
 */
    /**
     * Descargar de tablas
     */
    suspend fun downloadTablesInsertFromDB(entities:List<DownloadTableEntity>){
        downloadTableDAO.insertAll(entities)
    }

  //grabar tablas
    suspend fun answerAllFromDB(): List<AnswerEntity>? {
        return answerDAO.all()
    }

    suspend fun courseInsertList(entities: List<CourseEntity>){
        courseDAO.insertAll(entities)
    }

    /**
     * Ocupadas
     */
    suspend fun testTemplateInsertList(entities: List<TestTemplateEntity>){
        testTemplateDAO.insertAll(entities)
    }

    suspend fun topicInsertList(entities: List<TopicEntity>){
        topicDAO.insertAll(entities)
    }
    fun topicAll(): List<TopicEntity> {
        return topicDAO.all()
    }

    suspend fun questionInsertList(entities: List<QuestionEntity>){
        questionDAO.insertAll(entities)
    }

    suspend fun questionAll(): List<QuestionEntity> {
        return questionDAO.all()
    }

    suspend fun answerInsertList(answerEntities: List<AnswerEntity>){
        answerDAO.insertAll(answerEntities)
    }

    suspend fun answerAll(): List<AnswerEntity>? {
        return answerDAO.all()
    }

    //----------------------

    fun testTemplateAll(): List<TestTemplateEntity> {
        return testTemplateDAO.all()
    }


    suspend fun localParameterFindKeyFromDB(key:String):LocalParameterEntity{
        return localParameterDAO.findForKey(key)
    }

    suspend fun parameterLocalInsertFormDB(entity:LocalParameterEntity) {
        return localParameterDAO.insert(entity)
    }


    suspend fun parameterLocalUpdateFormDB(entity:LocalParameterEntity) {
        return localParameterDAO.update(entity)
    }
    fun appParameterFindForKey(schoolId:String, courseId:String, objectType:String, objectName :String ):AppParameterEntity{
        return parameterDAO.findForKey(schoolId, courseId,objectName, objectType)
    }
    suspend fun appParameterInsert(entity:AppParameterEntity){
        return parameterDAO.insert(entity)
    }

    fun parameterAll(): Flow<List<LocalParameterEntity>>{
        return localParameterDAO.all()
    }


}