package cl.daracenad.elearning.quiz.ui.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class  ProfileViewModel   @Inject constructor(
    //private val profileUpdate: UserProfileUpdate
): ViewModel() {
    fun register(name: String, email:String, password:String, password_confirmation:String){
        viewModelScope.launch(Dispatchers.IO) {
            //val result = profileUpdate.invoke(name, email)

            //if(result != null)
                //registerResponse.postValue(result!!)


            var rtn = "courseDownloadSrv.invoke()"

        }

    }

}