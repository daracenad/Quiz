package cl.daracenad.elearning.quiz.ui.school.download.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.daracenad.elearning.quiz.R
import cl.daracenad.elearning.quiz.databinding.ActivityCoursesForInstallationBinding
import cl.daracenad.elearning.quiz.ui.school.model.CourseSelected

class CoursesForInstallationAdapter(
    var lstCourses:List<CourseSelected>,
    val onClickListener:(CourseSelected, Boolean) -> Unit
): RecyclerView.Adapter<CourseViewHolder>() {
    private lateinit var binding: ActivityCoursesForInstallationBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {//CourseViewHolder {
        return CourseViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_coures_download, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return lstCourses.size
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.render(lstCourses[position],onClickListener)
    }

}
