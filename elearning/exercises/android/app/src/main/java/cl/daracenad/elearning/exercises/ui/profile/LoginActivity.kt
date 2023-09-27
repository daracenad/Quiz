package cl.daracenad.elearning.exercises.ui.profile

import android.content.Intent
import android.os.Bundle

import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import cl.daracenad.elearning.exercises.databinding.ActivityLoginBinding
import cl.daracenad.elearning.exercises.ui.sys.AppCheckActivity
import cl.daracenad.elearning.exercises.utils.usercase.DTOResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityLoginBinding

    private val viewmodel: LoginVM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        bindObservers()

    }

    fun performLogin(view: View) {
        _binding.tvMessage.text = ""
        viewmodel.login(_binding.etEMail.text.toString(), _binding.etPassword.text.toString())
    }
    fun performRegister(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goCheckStatusSystem() {
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