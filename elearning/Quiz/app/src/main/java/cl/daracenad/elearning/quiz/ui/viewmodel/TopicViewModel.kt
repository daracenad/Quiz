package cl.daracenad.elearning.quiz.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import cl.daracenad.elearning.quiz.data.RepositoryDownloadDB
import cl.daracenad.elearning.quiz.data.database.entities.LocalParameterEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopicViewModel @Inject constructor(
    private val repository: RepositoryDownloadDB
) : ViewModel() {
    val readData = repository.parameterAll().asLiveData()

    fun start(){
        //Verficar si el usario existe en local

    }
    fun searchDatabase(): LiveData<List<LocalParameterEntity>> {
        return repository.parameterAll().asLiveData()
    }


    fun insertData(entity: LocalParameterEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.parameterLocalInsertFormDB(entity)
        }
    }
}