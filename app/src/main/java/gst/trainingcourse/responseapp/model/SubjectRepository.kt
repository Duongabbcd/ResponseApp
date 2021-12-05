package gst.trainingcourse.responseapp.model

import android.database.Cursor
import androidx.lifecycle.LiveData

class SubjectRepository(private val subjectDAO: SubjectDAO) {

    val readAllData: Cursor =subjectDAO.readAllData()

    suspend fun addSubject(subject: Subject){
        subjectDAO.addSubject(subject)
    }

    suspend fun updateSubject(subject: Subject){
        subjectDAO.updateSubject(subject)
    }

    suspend fun deleteSubject(subject: Subject){
        subjectDAO.deleteSubject(subject)
    }

    suspend fun deleteAllSubjects(){
        subjectDAO.deleteAllSubjects()
    }
}