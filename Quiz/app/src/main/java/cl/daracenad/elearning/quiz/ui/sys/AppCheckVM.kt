package cl.daracenad.elearning.quiz.ui.sys


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.daracenad.elearning.quiz.domain.usecase.user.AppInfoLocalUC
import cl.daracenad.elearning.quiz.domain.model.AppStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class AppCheckVM @Inject constructor(
    val appInfoLocalUC: AppInfoLocalUC
) : ViewModel() {

    private var _listAppStatus = MutableLiveData<List<AppStatus>?>()
    val listAppStatus: MutableLiveData<List<AppStatus>?> get() = _listAppStatus

    private var _loadingAppStatus = MutableLiveData<AppStatus>()
    val loadingAppStatus: LiveData<AppStatus> get() = _loadingAppStatus


    //Verifica si el usuario existe en la app

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            /*
            val appStatus=  AppStatus(APIConst.GO_LOGIN, "Solicitar registar")
            insert(appStatus)
            _loadingAppStatus.postValue(appStatus)

             */

            try {
                var appStatus = appInfoLocalUC.invoke()

                insert(appStatus)
                _loadingAppStatus.postValue(appStatus)
            } catch (e: IOException) {
                val appStatus = AppStatus(666, "${e.message}")
                insert(appStatus)
                _loadingAppStatus.postValue(appStatus)
            } catch (e: UnknownHostException) {
                val appStatus = AppStatus(666, "${e.message}")
                insert(appStatus)
                _loadingAppStatus.postValue(appStatus)
            } catch (e: SocketTimeoutException) {
                val appStatus = AppStatus(666, "${e.message}")
                insert(appStatus)
                _loadingAppStatus.postValue(appStatus)
            } catch (e: Exception) {
                val appStatus = AppStatus(666, "${e.message}")
                insert(appStatus)
                _loadingAppStatus.postValue(appStatus)
            }

        }
    }

    private fun insert(entity: AppStatus) {

        /*
                val _list = _listAppStatus.value!! + entity
        _listAppStatus.postValue(_list)

         */
        var list = _listAppStatus.value

        if (list != null) {
            list = list + entity
            //_listAppStatus.postValue(listOf(entity))
            _listAppStatus.postValue(list)
        } else {
            _listAppStatus.postValue(listOf(entity))
        }


    }

}
