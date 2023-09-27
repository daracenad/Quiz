package cl.daracenad.elearning.exercises.domain.usecase.school.course

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.daracenad.elearning.exercises.data.RepositorySchoolDB
import cl.daracenad.elearning.exercises.domain.model.school.Course
import cl.daracenad.elearning.exercises.domain.model.school.School
import cl.daracenad.elearning.exercises.utils.usercase.DTOResult
import javax.inject.Inject

class CoursesForInstallationUC @Inject constructor(
    private val repositorySchoolDB: RepositorySchoolDB
) {
    private val _dtoResultLD = MutableLiveData<DTOResult<List<Course>>>()
    val dtoResultLD: LiveData<DTOResult<List<Course>>>
        get() = _dtoResultLD

        suspend operator fun invoke() {
            _dtoResultLD.postValue(DTOResult.Loading())

            try {
                _dtoResultLD.postValue(DTOResult.Success(
                    repositorySchoolDB.coursesPendingInstallation()
                ))
            }catch(ex: Exception){
                Log.e("msgdad CoursesForInstallationUC","${ex.message}")
                _dtoResultLD.postValue(DTOResult.Error("DB : ${ex.message}"))
            }
        }

}