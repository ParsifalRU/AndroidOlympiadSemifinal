package com.example.androidolympiadsemifinal.view

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
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

class ItemFragment(private val position: Int, private val projectsData: ServiceModel) : Fragment() {

    private lateinit var binding: FragmentItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createMenu()

        setData()

        onClick()
    }

    private fun setData(){
        downloadImage(requireContext(), binding.imageView, projectsData.items[position].icon_url)
        binding.nameTextView.text = projectsData.items[position].name
        binding.infoTextView.text = projectsData.items[position].description
        binding.urlTextView.text = projectsData.items[position].service_url
    }

    private fun onClick(){
        binding.urlTextView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("${binding.urlTextView.text}"))
            startActivity(intent)
        }
    }

    private fun createMenu(){
        val toolbar: androidx.appcompat.widget.Toolbar = requireActivity()
            .findViewById<View>(R.id.toolbar) as androidx.appcompat.widget.Toolbar
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
        toolbar.title = projectsData.items[position].name
        toolbar.setTitleTextColor(Color.WHITE)

        toolbar.setNavigationOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.list_fragment, ListFragment())
                .commit()
        }
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