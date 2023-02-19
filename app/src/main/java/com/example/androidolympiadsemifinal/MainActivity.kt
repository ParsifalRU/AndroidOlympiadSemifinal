package com.example.androidolympiadsemifinal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.androidolympiadsemifinal.network.VkProjectsApp
import com.example.androidolympiadsemifinal.viewmodel.VkProjectsViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vm = ViewModelProvider(this)[VkProjectsViewModel::class.java]
        vm.str((this?.application as? VkProjectsApp)?.vkProjectsApi)

    }

}