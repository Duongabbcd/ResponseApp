package gst.trainingcourse.responseapp.model

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SubjectDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSubject(subject: Subject)

    @Update
    suspend fun updateSubject(subject:Subject)

    @Delete
    suspend fun deleteSubject(subject: Subject)

    @Query("Delete from subject_table")
    suspend fun deleteAllSubjects()

    @Query("SELECT * from subject_table order by id ASC ")
    fun readAllData():Cursor
}