package cl.daracenad.elearning.quiz.data.database.dao

import android.database.sqlite.SQLiteException
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cl.daracenad.elearning.quiz.data.database.entities.AppParameterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IAppParameterDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLiteException::class)
    suspend fun insert(entity: AppParameterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLiteException::class)
    suspend fun insertAll(entity: List<AppParameterEntity>)

    @Update
    suspend fun update(vararg entity: AppParameterEntity)

    @Delete
    suspend fun delete(vararg entity: AppParameterEntity)

    @Query("SELECT * FROM " + AppParameterEntity.TABLE_NAME)
    fun all(): Flow<List<AppParameterEntity>>

    @Query("select * from ${AppParameterEntity.TABLE_NAME}  " +
            "where " +
            "schoolId = :schoolId and " +
            "courseId = :courseId and " +
            "objectName = :objectName and " +
            "objectType = :objectType")
    fun findForKey(schoolId:String, courseId:String, objectName:String, objectType:String): AppParameterEntity

}