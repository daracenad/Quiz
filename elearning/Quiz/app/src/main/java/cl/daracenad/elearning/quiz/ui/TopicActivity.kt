package cl.daracenad.elearning.quiz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import cl.daracenad.elearning.quiz.databinding.ActivityTopicBinding
import cl.daracenad.elearning.quiz.ui.adapter.TopicAdapter
import cl.daracenad.elearning.quiz.ui.viewmodel.TopicViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.appcompat.widget.SearchView
import cl.daracenad.elearning.quiz.data.database.entities.LocalParameterEntity
import kotlin.random.Random

@AndroidEntryPoint
class TopicActivity @Inject constructor() : AppCompatActivity(),
    SearchView.OnQueryTextListener{
    private lateinit var binding: ActivityTopicBinding

    private val mainViewModel: TopicViewModel by viewModels()
    private val myAdapter: TopicAdapter by lazy { TopicAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_topic)

        binding = ActivityTopicBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.rvTopic.layoutManager = LinearLayoutManager(this)
        binding.rvTopic.adapter = myAdapter

        mainViewModel.readData.observe(this, {
            myAdapter.setData(it)
        })

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    fun btnVer(view: View){
        var randomGenerator = Random(System.currentTimeMillis())
        var result = randomGenerator.nextInt(30, 50)


        var entity =  LocalParameterEntity("${result}","uno", 1, null)
        mainViewModel.insertData(entity)

        searchDatabase("")
    }
    fun putApp(view:View){
        val entity =  LocalParameterEntity("Uno","uno", 1, null)
        mainViewModel.insertData(entity)
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"

        mainViewModel.searchDatabase().observe(this, { list ->
            list.let {

                myAdapter.setData(it)
            }
        })
    }
}