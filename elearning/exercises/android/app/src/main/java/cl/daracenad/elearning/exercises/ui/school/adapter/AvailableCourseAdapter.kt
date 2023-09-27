package cl.daracenad.elearning.exercises.ui.school.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import cl.daracenad.elearning.exercises.R
import cl.daracenad.elearning.exercises.databinding.ActivityMatriculateBinding
import cl.daracenad.elearning.exercises.domain.model.school.Course
import cl.daracenad.elearning.exercises.ui.school.model.CourseSelected


class AvailableCourseAdapter(
    var lstCourses:List<CourseSelected>,
    val onClickListener:(CourseSelected, Boolean) -> Unit
): RecyclerView.Adapter<CourseViewHolder>() {
    private lateinit var binding:ActivityMatriculateBinding
    //class CourseViewHolder(val binding: CardCourseBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {//CourseViewHolder {
        return CourseViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_course, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return lstCourses.size
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        //holder.binding.chkCuorse.text = lstCourses[position].name.toString()
        holder.render(lstCourses[position],onClickListener)
    }

}
