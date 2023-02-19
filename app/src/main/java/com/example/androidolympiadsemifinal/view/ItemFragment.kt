package com.example.androidolympiadsemifinal.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidolympiadsemifinal.R
import com.example.androidolympiadsemifinal.databinding.FragmentItemBinding
import com.example.androidolympiadsemifinal.databinding.FragmentListBinding

class ItemFragment : Fragment() {

    private lateinit var binding: FragmentItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentItemBinding.inflate(inflater, container, false)
        return binding.root
    }

}