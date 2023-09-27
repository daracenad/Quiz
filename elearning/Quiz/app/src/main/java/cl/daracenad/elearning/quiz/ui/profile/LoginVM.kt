package cl.daracenad.elearning.quiz.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.daracenad.elearning.quiz.domain.model.school.School
import cl.daracenad.elearning.quiz.domain.usecase.profile.LoginUC
import cl.daracenad.elearning.quiz.utils.usercase.DTOResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(
    private val loginUC: LoginUC
) : ViewModel() {
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        message.postValue("error de comunicación")
    }
    val userAPILiveData: LiveData<DTOResult<School>>
        get() = loginUC.userResponseLiveData

    val message = MutableLiveData<String>()

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            loginUC.invoke(email, password)

        }
    }

}


