package cl.daracenad.elearning.exercises.ui.profile


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.daracenad.elearning.exercises.domain.model.school.School
import cl.daracenad.elearning.exercises.domain.usecase.profile.RegisterUC
import cl.daracenad.elearning.exercises.utils.usercase.DTOResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterVM @Inject constructor(
    private val registerUC: RegisterUC
) : ViewModel() {

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        message.postValue("Error de conexión")
    }

    val userAPILiveData: LiveData<DTOResult<School>>
        get() = registerUC.userResponseLiveData

    val message = MutableLiveData<String>()

    fun register(email: String, name: String, password: String, passwordConfirm: String) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            registerUC.invoke(email, name, password, passwordConfirm)
        }
    }
}

