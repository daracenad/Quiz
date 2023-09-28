package cl.daracenad.elearning.quiz.data.database.dao

import android.database.sqlite.SQLiteException
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

import cl.daracenad.elearning.quiz.data.database.entities.LocalParameterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ILocalParameterDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLiteException::class)
    suspend fun insert(entity: LocalParameterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLiteException::class)
    suspend fun insertAll(entity: List<LocalParameterEntity>)

    @Update
    suspend fun update(vararg entity: LocalParameterEntity)

    @Delete
    suspend fun delete(vararg entity: LocalParameterEntity)

    @Query("SELECT * FROM " + LocalParameterEntity.TABLE_NAME)
    fun all(): Flow<List<LocalParameterEntity>>

    @Query("SELECT * FROM " + LocalParameterEntity.TABLE_NAME + " where key = :key")
    fun findForKey(key:String): LocalParameterEntity

}