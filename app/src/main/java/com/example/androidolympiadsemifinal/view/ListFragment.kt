package com.example.androidolympiadsemifinal.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidolympiadsemifinal.R
import com.example.androidolympiadsemifinal.databinding.FragmentListBinding
import com.example.androidolympiadsemifinal.model.ServiceModel
import com.example.androidolympiadsemifinal.network.VkProjectsApp
import com.example.androidolympiadsemifinal.view.adapter.RecyclerViewAdapter
import com.example.androidolympiadsemifinal.viewmodel.VkProjectsViewModel

class ListFragment : Fragment(), RecyclerViewAdapter.Listener {

    private lateinit var binding: FragmentListBinding
    private lateinit var dataList: ServiceModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        getData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolBar()
    }

    private fun setAdapter(dataList: ServiceModel){
        val adapter = RecyclerViewAdapter(dataList, requireContext(), this)
        val recyclerView = binding.projectsRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter.refreshUI()
    }

    private fun getData(){
        val viewModel = ViewModelProvider(this)[VkProjectsViewModel::class.java]
        viewModel.getProjectsData((activity?.application as? VkProjectsApp)?.vkProjectsApi)
        viewModel.liveData.observe(viewLifecycleOwner) { data ->
            Toast.makeText(requireContext(), "Данные пришли", Toast.LENGTH_SHORT).show()
            setAdapter(data)
            binding.projectsRecyclerView.visibility = View.VISIBLE
            dataList = data
        }
        viewModel.liveDataError.observe(viewLifecycleOwner) { error ->
            binding.projectsRecyclerView.visibility = View.GONE
            Toast.makeText(
                requireActivity().baseContext,
                "Произошла ошибка, повторная попытка через 5 сек $error",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onClick(position: Int) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.list_fragment, ItemFragment(position,dataList))
            .commit()
    }

    private fun setToolBar(){
        val toolbar: androidx.appcompat.widget.Toolbar = requireActivity().findViewById<View>(R.id.toolbar_list) as androidx.appcompat.widget.Toolbar
        toolbar.title = "Проекты VK"
        toolbar.setTitleTextColor(Color.WHITE)
    }
}