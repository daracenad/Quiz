package cl.daracenad.elearning.quiz.data.database.dao

import android.database.sqlite.SQLiteException
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

import cl.daracenad.elearning.quiz.data.database.entities.AppParameterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IParameterDAO {
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

    @Query("SELECT * FROM " + AppParameterEntity.TABLE_NAME + " where key = :key")
    fun findForKey(key:String): AppParameterEntity

}