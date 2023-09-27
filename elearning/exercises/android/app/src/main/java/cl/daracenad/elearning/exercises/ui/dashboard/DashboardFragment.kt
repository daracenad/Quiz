package cl.daracenad.elearning.exercises.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cl.daracenad.elearning.exercises.databinding.FragmentDashboardBinding

import cl.daracenad.elearning.exercises.ui.adapter.TopicAdapter

class DashboardFragment : Fragment(),SearchView.OnQueryTextListener {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val mainViewModel: DashboardViewModel by viewModels()
    private val myAdapter: TopicAdapter by lazy { TopicAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _binding!!.rvTopic.layoutManager = LinearLayoutManager(requireContext())
        _binding!!.rvTopic.adapter = myAdapter

        mainViewModel.readData.observe(viewLifecycleOwner, {
            //myAdapter.setData(it)
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        TODO("Not yet implemented")
    }
}