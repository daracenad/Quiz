package cl.daracenad.elearning.quiz.ui.school.adapter

import android.view.View

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

import cl.daracenad.elearning.quiz.databinding.CardCourseBinding
import cl.daracenad.elearning.quiz.domain.model.school.Course
import cl.daracenad.elearning.quiz.ui.school.model.CourseSelected

class CourseViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val binding = CardCourseBinding.bind(view)

    fun render(courseSelected: CourseSelected, onClickListener:(CourseSelected, Boolean) -> Unit){
        binding.chkCuorse.text = courseSelected.course.name.toString()
        binding.chkCuorse.isChecked = courseSelected.selected
        binding.chkCuorse.setOnClickListener {
            //Toast.makeText(binding.chkCuorse.context,"click curso ${course.name}", Toast.LENGTH_LONG).show()
            courseSelected.selected = binding.chkCuorse.isChecked
            onClickListener(courseSelected, binding.chkCuorse.isChecked)
        }

    }
}