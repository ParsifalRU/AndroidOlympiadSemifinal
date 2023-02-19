package com.example.androidolympiadsemifinal

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.androidolympiadsemifinal.network.VkProjectsApp
import com.example.androidolympiadsemifinal.view.ListFragment
import com.example.androidolympiadsemifinal.viewmodel.VkProjectsViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFragment()
    }
    private fun setFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.list_fragment, ListFragment())
            .commit()
    }
}