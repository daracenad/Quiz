package cl.daracenad.elearning.quiz.ui.school.download

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import cl.daracenad.elearning.quiz.databinding.ActivityCoursesForInstallationBinding
import cl.daracenad.elearning.quiz.ui.school.download.adapter.CoursesForInstallationAdapter
import cl.daracenad.elearning.quiz.ui.school.model.CourseSelected
import cl.daracenad.elearning.quiz.utils.usercase.DTOResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoursesForInstallationActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityCoursesForInstallationBinding
    private val viewmodel: CoursesForInstallationVM by viewModels()
    lateinit var availableCourseAdapter: CoursesForInstallationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCoursesForInstallationBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        availableCourseAdapter = CoursesForInstallationAdapter(
            emptyList<CourseSelected>(),
            { course, isChecked -> onClickListener(course, isChecked) })
        _binding.rvCourses.apply {
            layoutManager = LinearLayoutManager(this@CoursesForInstallationActivity)
            adapter = availableCourseAdapter
        }

        bindObservers()

    }

    private fun bindObservers() {

        viewmodel.lstCoursesSelected.observe(this) {
            if (it != null) {
                availableCourseAdapter.lstCourses = it
                availableCourseAdapter.notifyDataSetChanged()
            }
        }

        viewmodel.coursesForIntallLD.observe(this) {
            _binding.loading.isVisible = false
            when (it) {
                is DTOResult.Success -> {
                    it.data.let { data -> viewmodel.loadCourses(data!!) }
                }

                is DTOResult.Error -> {
                    _binding.tvMessage.text = it.message
                }

                is DTOResult.Loading -> {

                    _binding.loading.isVisible = true
                }

                else -> {}
            }
        }

        viewmodel.coursesDownloadUCLD.observe(this){
            _binding.loading.isVisible = false
            when (it) {
                is DTOResult.Success -> {
                    _binding.tvMessage.text = it.message
                }

                is DTOResult.Error -> {
                    _binding.tvMessage.text = it.message
                }

                is DTOResult.Loading -> {
                    _binding.tvMessage.text = it.message
                    _binding.loading.isVisible =true
                }
                is DTOResult.Message->{
                    _binding.tvMessage.text = it.message

                }
                else -> {

                }
            }

        }

    }

    fun onClickListener(courseSelected: CourseSelected, isChecked: Boolean) {
        //viewmodel.modifyCoursesSelected(courseSelected, isChecked)
        Log.e("msgdad CoursesForInstallationActivity","${courseSelected}")
        viewmodel.downloadCourses(courseSelected.course)
    }


}