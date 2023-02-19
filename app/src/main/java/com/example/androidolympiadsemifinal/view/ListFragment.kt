package com.example.androidolympiadsemifinal.view

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidolympiadsemifinal.databinding.FragmentListBinding
import com.example.androidolympiadsemifinal.model.ServiceModel
import com.example.androidolympiadsemifinal.network.VkProjectsApp
import com.example.androidolympiadsemifinal.view.adapter.RecyclerViewAdapter
import com.example.androidolympiadsemifinal.viewmodel.VkProjectsViewModel

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListBinding.inflate(inflater, container, false)
        getData()
        return binding.root
    }

    fun infoFragment(){
        val intent = Intent(requireContext(), ItemFragment::class.java)
        startActivity(intent)
    }

    private fun setAdapter(dataList: ServiceModel){
        val adapter = RecyclerViewAdapter(dataList, requireContext())
        val recyclerView = binding.projectsRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter.refreshUI()
    }

    private fun getData(){
        val viewModel = ViewModelProvider(this)[VkProjectsViewModel::class.java]
        viewModel.getProjectsData((activity?.application as? VkProjectsApp)?.vkProjectsApi)
        viewModel.liveData.observe(viewLifecycleOwner, Observer{ data ->
            Toast.makeText(requireContext(), "Данные пришли", Toast.LENGTH_SHORT).show()
            setAdapter(data)
            binding.projectsRecyclerView.visibility = View.VISIBLE
        })
        viewModel.liveDataError.observe(viewLifecycleOwner, Observer{ error ->
            binding.projectsRecyclerView.visibility = View.GONE
            Toast.makeText(requireActivity().baseContext, "Повторная попытка через 5 сек $error", Toast.LENGTH_SHORT).show()
        })
    }
}