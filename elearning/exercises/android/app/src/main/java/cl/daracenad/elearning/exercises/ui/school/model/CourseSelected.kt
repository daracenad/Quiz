package cl.daracenad.elearning.exercises.ui.school.model


import cl.daracenad.elearning.exercises.domain.model.school.Course

data class CourseSelected(

   val course: Course,
   var selected:Boolean
){
   var id:String
      get()= course.id
      set(value) {course.id = value}

}