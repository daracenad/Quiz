package cl.daracenad.elearning.exercises.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import cl.daracenad.elearning.exercises.data.RepositoryDB
import cl.daracenad.elearning.exercises.data.database.entities.AppParameterEntity
import cl.daracenad.elearning.exercises.data.database.entities.TopicEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopicViewModel @Inject constructor(
    private val repository: RepositoryDB
) : ViewModel() {
    val readData = repository.parameterAll().asLiveData()

    fun start(){
        //Verficar si el usario existe en local

    }
    fun searchDatabase(): LiveData<List<AppParameterEntity>> {
        return repository.parameterAll().asLiveData()
    }


    fun insertData(entity: AppParameterEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.parameterInsertFormDB(entity)
        }
    }
}