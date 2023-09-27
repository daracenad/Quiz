package cl.daracenad.elearning.quiz.data.database.dao

import android.database.sqlite.SQLiteException
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

import cl.daracenad.elearning.quiz.data.database.entities.MatriculateEntity

@Dao
interface IMatriculateDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLiteException::class)
    suspend fun insert(entity: MatriculateEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLiteException::class)
    suspend fun insertAll(entity: List<MatriculateEntity>)

    @Update
    suspend fun update(vararg entity: MatriculateEntity)

    @Delete
    suspend fun delete(vararg entity: MatriculateEntity)

    @Query("SELECT * FROM " + MatriculateEntity.TABLE_NAME)
    fun all(): List<MatriculateEntity>

    @Query(value = "select exists(select * from  ${MatriculateEntity.TABLE_NAME})")
    fun exists(): Boolean

    @Query("select * from  ${MatriculateEntity.TABLE_NAME} where id  = :id" )
    fun findId(id: String ): LiveData<MatriculateEntity>
}