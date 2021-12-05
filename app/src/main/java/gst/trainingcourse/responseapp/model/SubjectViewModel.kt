package gst.trainingcourse.responseapp.model

import android.app.Application
import android.database.Cursor
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubjectViewModel(application :Application) : AndroidViewModel(application) {

     val readAllData : Cursor
    private val repository : SubjectRepository

    init {
        val subjectDAO =SubjectDatabase.getDatabase(application).subjectDAO()
        repository = SubjectRepository(subjectDAO)
        readAllData =repository.readAllData
    }

    fun addSubject(subject :Subject){
        viewModelScope.launch(Dispatchers.IO){
            repository.addSubject(subject)
        }
    }

    fun updateSubject(subject: Subject){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateSubject(subject)
        }
    }

    fun deleteSubject(subject: Subject){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteSubject(subject)
        }
    }

    fun deleteAllSubjects(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllSubjects()
        }
    }
}