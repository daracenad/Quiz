package cl.daracenad.elearning.exercises.ui.school.download

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.daracenad.elearning.exercises.domain.model.school.Course
import cl.daracenad.elearning.exercises.domain.usecase.school.course.CoursesForInstallationUC
import cl.daracenad.elearning.exercises.ui.school.model.CourseSelected
import cl.daracenad.elearning.exercises.utils.usercase.DTOResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoursesForInstallationVM @Inject constructor(
    private val coursesForInstallationUC: CoursesForInstallationUC
) : ViewModel(){

    //val coursesInProcessDownload:LiveData
    val coursesForIntallLD: LiveData<DTOResult<List<Course>>>
        get() = coursesForInstallationUC.dtoResultLD

    private var _listCoursesSelected = MutableLiveData<List<CourseSelected>?>()
    val lstCoursesSelected: MutableLiveData<List<CourseSelected>?> get() = _listCoursesSelected

    init {
        viewModelScope.launch(Dispatchers.IO) {
            coursesForInstallationUC.invoke()
        }
    }

    fun loadCourses(courses: List<Course>) {
        var coll: List<CourseSelected> = emptyList()
        if (courses != null)
            courses?.forEach { course ->
                coll += CourseSelected(course, false)
            }
        lstCoursesSelected.postValue(coll)
    }

    fun downloadCourses(course: Course){

    }

}