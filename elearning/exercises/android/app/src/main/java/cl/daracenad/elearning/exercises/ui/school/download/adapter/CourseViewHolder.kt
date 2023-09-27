package cl.daracenad.elearning.exercises.ui.school.download.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cl.daracenad.elearning.exercises.databinding.CardCouresDownloadBinding
import cl.daracenad.elearning.exercises.ui.school.model.CourseSelected

class CourseViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val binding = CardCouresDownloadBinding.bind(view)

    fun render(courseSelected: CourseSelected, onClickListener:(CourseSelected, Boolean) -> Unit){
        binding.tvCourseDownload.text = courseSelected.course.name.toString()

        binding.btnCourseDownload.setOnClickListener {
            onClickListener(courseSelected, true)
        }

    }
}