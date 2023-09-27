package cl.daracenad.elearning.exercises.ui.profile

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import cl.daracenad.elearning.exercises.databinding.ActivityRegisterBinding
import cl.daracenad.elearning.exercises.di.RoomModule
import cl.daracenad.elearning.exercises.ui.sys.AppCheckActivity
import cl.daracenad.elearning.exercises.utils.usercase.DTOResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityRegisterBinding

    private val viewmodel: RegisterVM by viewModels()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        val view = _binding.root
        setContentView(view)
        bindObservers()
    }

    fun btnGoToConfirmation(v: View) {
        _binding.let {
            viewmodel.register(
                "${it.etEMail.text}",
                "${it.etNombres.text}",
                "${it.etPassword.text}",
                "${it.etPasswordConfirmation.text}")
        }
    }
    fun btnDelete(v:View){
        lifecycleScope.launch {
            applicationContext.deleteDatabase(RoomModule.DATABASE_NAME)

            Toast.makeText(applicationContext, "UserSerialize Information is Delete", Toast.LENGTH_SHORT)
                .show()
        }
    }
    private fun goAppCheckActivity() {
        val intent = Intent(this, AppCheckActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun bindObservers(){
        viewmodel.message.observe(this, {
            _binding.loading.isVisible = false
            _binding.tvMessage.text = it
        })

        viewmodel.userAPILiveData.observe(this, {
            _binding.loading.isVisible = false

            when (it) {
                is DTOResult.Success -> {
                    _binding.tvMessage.text = it.message
                    goAppCheckActivity()

                }

                is DTOResult.Error -> {
                    _binding.tvMessage.text = it.message
                }
                is DTOResult.Loading -> {

                    _binding.loading.isVisible = true
                }
            }
        })

    }


}