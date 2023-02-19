package com.example.androidolympiadsemifinal.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.androidolympiadsemifinal.R
import com.example.androidolympiadsemifinal.databinding.FragmentItemBinding
import com.example.androidolympiadsemifinal.model.ServiceModel

class ItemFragment(val position: Int, val projectsData: ServiceModel) : Fragment() {

    private lateinit var binding: FragmentItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentItemBinding.inflate(inflater, container, false)

        setData()

        return binding.root
    }

    fun setData(){
        downloadImage(requireContext(), binding.imageView, projectsData.items[position].icon_url)
        binding.nameTextView.text = projectsData.items[position].name
        binding.infoTextView.text = projectsData.items[position].description
        binding.urlTextView.text = projectsData.items[position].service_url
    }

    private fun downloadImage(context: Context, imageView: ImageView, url: String){
        Glide
            .with(context)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.baseline_loop_24)
            .into(imageView)
    }

}