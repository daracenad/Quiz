package cl.daracenad.elearning.exercises.data.database.dao

import android.database.sqlite.SQLiteException
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import cl.daracenad.elearning.exercises.data.database.entities.SchoolEntity
import cl.daracenad.elearning.exercises.domain.model.school.School

@Dao
interface ISchoolDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLiteException::class)
    suspend fun insert(entity: SchoolEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLiteException::class)
    suspend fun insertAll(entity: List<SchoolEntity>)

    @Update
    suspend fun update(vararg entity: SchoolEntity)

    @Delete
    suspend fun delete(vararg entity: SchoolEntity)

    @Query("SELECT * FROM " + SchoolEntity.TABLE_NAME)
    fun all(): List<SchoolEntity>

    @Query("SELECT * FROM " + SchoolEntity.TABLE_NAME + " where id = :id")
    fun findSchoolForId(id:String): LiveData<SchoolEntity>
}