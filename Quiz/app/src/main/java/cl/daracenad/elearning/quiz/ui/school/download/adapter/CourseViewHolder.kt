package cl.daracenad.elearning.quiz.ui.school.download.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cl.daracenad.elearning.quiz.databinding.CardCouresDownloadBinding
import cl.daracenad.elearning.quiz.ui.school.model.CourseSelected

class CourseViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val binding = CardCouresDownloadBinding.bind(view)

    fun render(courseSelected: CourseSelected, onClickListener:(CourseSelected, Boolean) -> Unit){
        binding.tvCourseDownload.text = courseSelected.course.name.toString()

        binding.btnCourseDownload.setOnClickListener {
            onClickListener(courseSelected, true)
        }

    }
}