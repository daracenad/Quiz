package cl.daracenad.elearning.quiz.ui.school

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.daracenad.elearning.quiz.data.RepositorySchoolDB
import cl.daracenad.elearning.quiz.domain.UserProfileUC
import cl.daracenad.elearning.quiz.domain.model.school.School
import cl.daracenad.elearning.quiz.domain.services.app.AppStudentIdGetSrv
import cl.daracenad.elearning.quiz.domain.usecase.school.MatriculateUC
import cl.daracenad.elearning.quiz.ui.school.model.CourseSelected
import cl.daracenad.elearning.quiz.utils.usercase.DTOResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatriculateVM @Inject constructor(
    private val userProfileUC: UserProfileUC, //obtiene el perfil
    private val matriculateUC: MatriculateUC, //matricula
    private val appStudentIdGetSrv: AppStudentIdGetSrv,
    private val repositorySchoolDB: RepositorySchoolDB
) : ViewModel() {

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        message.postValue("error de comunicación")
    }
    val matriculateLD: LiveData<DTOResult<School>>
        get() = matriculateUC.dtoResultLD

    val profileLD: LiveData<DTOResult<School>>
        get() = userProfileUC.dtoResultLD

    val message = MutableLiveData<String>()

    private var _listCoursesSelected = MutableLiveData<List<CourseSelected>?>()
    val lstCoursesSelected: MutableLiveData<List<CourseSelected>?> get() = _listCoursesSelected

// para borrar



    // para borrar
    init {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            userProfileUC.invoke()
        }
    }

    //solicitud de carga de curso desde la activity
    fun loadCourses(school: School) {
        var coll: List<CourseSelected> = emptyList()
        if (school != null)
            school?.courses?.forEach { course ->

                coll += CourseSelected(course, false)

            }
        lstCoursesSelected.postValue(coll)
    }

    fun goMatriculate() {
        viewModelScope.launch(Dispatchers.IO) {
            var id: List<String> = emptyList()
            lstCoursesSelected.value?.forEach {
                if (it.selected) {
                    id += it.course.id
                }
            }

            if (id.size > 0)
                matriculateUC.invoke(id)
            else
                Log.e("MatriculateVM", "no se encontro seleccionados")
        }


    }


    fun modifyCoursesSelected(courseSelected: CourseSelected, checked: Boolean) {
        Log.e("dadmsg","modifyCoursesSelected")
    if(checked){
        courseSelected.id = courseSelected.id?.toMutableList()?.apply {
            removeAt(0)
        }?.toList().toString()
    }

    }


}

