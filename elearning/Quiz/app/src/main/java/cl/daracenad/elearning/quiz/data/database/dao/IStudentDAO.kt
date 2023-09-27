package cl.daracenad.elearning.quiz.data.database.dao

import android.database.sqlite.SQLiteException
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cl.daracenad.elearning.quiz.data.database.entities.StudentEntity
import org.jetbrains.annotations.NotNull

@Dao
interface IStudentDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLiteException::class)
    suspend fun insert(entity: StudentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLiteException::class)
    suspend fun insertAll(entity: StudentEntity)

    @Update
    suspend fun update(vararg entity: StudentEntity)

    @Delete
    suspend fun delete(vararg entity: StudentEntity)

    @Query("SELECT * FROM ${StudentEntity.TABLE_NAME}" )
    fun all(): LiveData<List<StudentEntity>>


    @Query("select * from ${StudentEntity.TABLE_NAME} where id=:id" )
    fun findForId(@NotNull id:String): StudentEntity

}