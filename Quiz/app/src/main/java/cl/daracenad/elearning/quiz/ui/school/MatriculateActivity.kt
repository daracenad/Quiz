package cl.daracenad.elearning.quiz.ui.school

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import cl.daracenad.elearning.quiz.databinding.ActivityMatriculateBinding
import cl.daracenad.elearning.quiz.ui.sys.AppCheckActivity
import cl.daracenad.elearning.quiz.ui.school.adapter.AvailableCourseAdapter
import cl.daracenad.elearning.quiz.ui.school.model.CourseSelected
import cl.daracenad.elearning.quiz.utils.usercase.DTOResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatriculateActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMatriculateBinding
    private val viewmodel: MatriculateVM by viewModels()

    //private val adapter : AvailableCourseAdapter by lazy { AvailableCourseAdapter() }
    lateinit var availableCourseAdapter : AvailableCourseAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMatriculateBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        availableCourseAdapter = AvailableCourseAdapter(emptyList<CourseSelected>(), {course,isChecked -> onClickListener(course, isChecked)})
        _binding.rvCourses.apply {
            layoutManager = LinearLayoutManager(this@MatriculateActivity)
            adapter = availableCourseAdapter
        }

        bindObservers()
    }

    private fun bindObservers() {
        viewmodel.lstCoursesSelected.observe(this){
            if(it != null){
                availableCourseAdapter.lstCourses = it
                availableCourseAdapter.notifyDataSetChanged()
            }
        }

        viewmodel.profileLD.observe(/* owner = */ this) /* observer = */ {
            _binding.matriculateProgressBar.isVisible = false
            when (it) {
                is DTOResult.Success -> {
                    it.data?.let { school -> viewmodel.loadCourses(school) }
                    _binding.tvNombre.text = it.data?.student!!.name
                }

                is DTOResult.Error -> {
                    _binding.tvMessage.text = it.message
                }

                is DTOResult.Loading -> {

                    _binding.matriculateProgressBar.isVisible = true
                }
            }
        }

        viewmodel.matriculateLD.observe(this) {
            _binding.matriculateProgressBar.isVisible = false
            when (it) {
                is DTOResult.Success -> {
                    goAppCheck()
                }
                is DTOResult.Error -> {
                    _binding.tvMessage.text = it.message
                }
                is DTOResult.Loading -> {
                    _binding.matriculateProgressBar.isVisible = true
                }
            }
        }
    }
    fun btnEnrolled(view: View){
        viewmodel.goMatriculate()
    }
    fun onClickListener(courseSelected:CourseSelected, isChecked:Boolean){
        //viewmodel.modifyCoursesSelected(courseSelected, isChecked)
        //Toast.makeText(this@MatriculateActivity,"click curso ${courseSelected.course.name} ${isChecked}", Toast.LENGTH_LONG).show()
    }
    private fun goAppCheck() {
        val intent = Intent(this, AppCheckActivity::class.java)
        startActivity(intent)
        finish()
    }
}