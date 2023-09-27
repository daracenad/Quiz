package cl.daracenad.elearning.quiz.ui.school.model


import cl.daracenad.elearning.quiz.domain.model.school.Course

data class CourseSelected(

   val course: Course,
   var selected:Boolean
){
   var id:String
      get()= course.id
      set(value) {course.id = value}

}