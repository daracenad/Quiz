package cl.daracenad.elearning.exercises.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import cl.daracenad.elearning.exercises.R
import cl.daracenad.elearning.exercises.databinding.ActivityProfileBinding
import cl.daracenad.elearning.exercises.ui.viewmodel.ProfileViewModel

class ProfileActivity : AppCompatActivity() {
    private lateinit var  binding:ActivityProfileBinding
    private val viewmodel: ProfileViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun btnActualizar(view: View){

    }
}