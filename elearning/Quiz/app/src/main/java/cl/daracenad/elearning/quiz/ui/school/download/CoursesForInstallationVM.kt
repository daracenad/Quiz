package cl.daracenad.elearning.quiz.ui.school.download

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.daracenad.elearning.quiz.data.RepositoryDownloadDB
import cl.daracenad.elearning.quiz.domain.model.school.Course
import cl.daracenad.elearning.quiz.domain.usecase.school.course.download.CoursesDownloadUC
import cl.daracenad.elearning.quiz.domain.usecase.school.course.CoursesForInstallationUC
import cl.daracenad.elearning.quiz.ui.school.model.CourseSelected
import cl.daracenad.elearning.quiz.utils.usercase.DTOResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoursesForInstallationVM @Inject constructor(
    private val coursesForInstallationUC: CoursesForInstallationUC,
    private val coursesDownloadUC: CoursesDownloadUC
) : ViewModel(){

    //val coursesInProcessDownload:LiveData
    val coursesForIntallLD: LiveData<DTOResult<List<Course>>>
        get() = coursesForInstallationUC.dtoResultLD

    val coursesDownloadUCLD: LiveData<DTOResult<String>>
        get() = coursesDownloadUC.dtoResultLD

    private var _listCoursesSelected = MutableLiveData<List<CourseSelected>?>()
    val lstCoursesSelected: MutableLiveData<List<CourseSelected>?> get() = _listCoursesSelected

    init {
        viewModelScope.launch(Dispatchers.IO) {

            coursesForInstallationUC.invoke()
            /*
            val a = repositoryDownloadDB.topicAll()
            Log.e("msgdad CoursesForInstallationVM","${a}")
            val b=  repositoryDownloadDB.testTemplateAll()
            Log.e("msgdad CoursesForInstallationVM","${b}")
            val c=  repositoryDownloadDB.questionAll()
            Log.e("msgdad CoursesForInstallationVM","${c}")
            val d=  repositoryDownloadDB.answerAll()
            Log.e("msgdad CoursesForInstallationVM","${d}")

             */
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
        viewModelScope.launch(Dispatchers.IO) {
                coursesDownloadUC.invoke(course)

        }
    }

}