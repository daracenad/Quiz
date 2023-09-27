package cl.daracenad.elearning.quiz.ui.sys

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import cl.daracenad.elearning.quiz.R
import cl.daracenad.elearning.quiz.databinding.ActivityAppCheckBinding


import cl.daracenad.elearning.quiz.utils.http.WifiService
import cl.daracenad.elearning.quiz.di.RoomModule
import cl.daracenad.elearning.quiz.domain.model.APIConst.Companion.GO_DOWNLOAD_COURSE
import cl.daracenad.elearning.quiz.domain.model.APIConst.Companion.GO_LOGIN
import cl.daracenad.elearning.quiz.domain.model.APIConst.Companion.GO_MATRICULATE
import cl.daracenad.elearning.quiz.domain.model.APIConst.Companion.OK
import cl.daracenad.elearning.quiz.domain.model.APIConst.Companion.GO_PROFILE_ACTUALIZATE
import cl.daracenad.elearning.quiz.domain.model.APIConst.Companion.GO_REGISTER
import cl.daracenad.elearning.quiz.ui.MainActivity
import cl.daracenad.elearning.quiz.ui.ProfileActivity

import cl.daracenad.elearning.quiz.ui.adapter.AppStatusAdapter
import cl.daracenad.elearning.quiz.ui.profile.LoginActivity
import cl.daracenad.elearning.quiz.ui.profile.RegisterActivity
import cl.daracenad.elearning.quiz.ui.school.MatriculateActivity
import cl.daracenad.elearning.quiz.ui.school.download.CoursesForInstallationActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class AppCheckActivity @Inject constructor(

): AppCompatActivity() {

    private val appCheckVM: AppCheckVM by viewModels()
    private lateinit var binding: ActivityAppCheckBinding
    private val adapter : AppStatusAdapter by lazy { AppStatusAdapter() }


    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_check)

        binding = ActivityAppCheckBinding.inflate(layoutInflater)

        //setContentView(binding.root)

        //binding.rvAppStatus.layoutManager = LinearLayoutManager(this)
        //binding.rvAppStatus.adapter = adapter


        WifiService.instance.initializeWithApplicationContext(this)
        appCheckVM.listAppStatus.observe(this){
            if (it != null) {
                adapter.listAppStatus = it
                adapter.notifyDataSetChanged()
            }

        }
        appCheckVM.loadingAppStatus.observe(this){
            when(it.rtn){
                GO_REGISTER-> performRegister()
                GO_LOGIN-> performLogin()
                GO_PROFILE_ACTUALIZATE-> performProfile()
                GO_MATRICULATE-> performMatriculate()
                GO_DOWNLOAD_COURSE -> performDownloadCourse()
                OK-> performHome()
            }
        }

        appCheckVM.init()
    }

    private fun performDownloadCourse() {
        val intent  = Intent(this, CoursesForInstallationActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun performProfile(){
        Toast.makeText(applicationContext, "Profile", Toast.LENGTH_SHORT)
            .show()
        val intent  = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
        finish()

    }

    private fun performHome(){
        Toast.makeText(applicationContext, "UserSerialize Information is Delete", Toast.LENGTH_SHORT)
            .show()

        val intent  = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

    }
    private fun performRegister(){
        val intent  = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun performLogin(){
        val intent  = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun performMatriculate(){
        val intent  = Intent(this, MatriculateActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun btnDelete(view:View){
        lifecycleScope.launch {
            applicationContext.deleteDatabase(RoomModule.DATABASE_NAME)

            Toast.makeText(applicationContext, "UserSerialize Information is Delete", Toast.LENGTH_SHORT)
                .show()
        }

    }


}