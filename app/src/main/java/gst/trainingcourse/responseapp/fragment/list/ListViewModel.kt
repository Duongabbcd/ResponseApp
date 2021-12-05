package gst.trainingcourse.responseapp.fragment.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModel :ViewModel() {
    private val _toAddFragment = MutableLiveData<Boolean>()
    val toAddFragment: LiveData<Boolean>
        get() = _toAddFragment

    fun onStart(){
        _toAddFragment.value=true
    }

    fun onStartFinished(){
        _toAddFragment.value=null
    }
}