package com.example.androidolympiadsemifinal.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.androidolympiadsemifinal.network.VkProjectsApi

class VkProjectsViewModel(application: Application):AndroidViewModel(application) {
    private var isStarted: Boolean = false

    fun getProjectsData(){
        isStarted = true
        Thread(object : Runnable {
            override fun run() {
                while (isStarted){

                }
            }
        }).start()
    }

    fun str(vkProjectsApi: VkProjectsApi?){
        vkProjectsApi?.getProjectsData()
    }

    override fun onCleared() {
        super.onCleared()
        isStarted = false
    }

}