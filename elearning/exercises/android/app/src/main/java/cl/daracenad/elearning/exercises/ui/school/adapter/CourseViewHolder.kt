package cl.daracenad.elearning.exercises.ui.school.adapter

import android.view.View

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

import cl.daracenad.elearning.exercises.databinding.CardCourseBinding
import cl.daracenad.elearning.exercises.domain.model.school.Course
import cl.daracenad.elearning.exercises.ui.school.model.CourseSelected

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