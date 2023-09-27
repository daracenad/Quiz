package cl.daracenad.elearning.exercises.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import cl.daracenad.elearning.exercises.data.RepositoryDB
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    repository: RepositoryDB
): ViewModel() {
    val readData = repository.topicFromDBAll().asLiveData()


}