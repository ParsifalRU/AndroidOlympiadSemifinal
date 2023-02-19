package com.example.androidolympiadsemifinal.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.androidolympiadsemifinal.model.ServiceModel
import com.example.androidolympiadsemifinal.network.VkProjectsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VkProjectsViewModel(application: Application):AndroidViewModel(application) {
    val liveData = MutableLiveData<ServiceModel>()
    val liveDataError = MutableLiveData<Throwable>()
    private var isStarted: Boolean = false

    fun getProjectsData(vkProjectsApi: VkProjectsApi?){
        isStarted = true
        Thread(object : Runnable {
            override fun run() {
                while (isStarted){
                    val t = vkProjectsApi?.getProjectsData()
                    t?.enqueue(object: Callback<ServiceModel> {
                         override fun onResponse(call: Call<ServiceModel>, response: Response<ServiceModel>) {
                             liveData.value = response.body()
                             isStarted = false
                         }
                         override fun onFailure(call: Call<ServiceModel>, t: Throwable) {
                             liveDataError.value = t

                         }
                    })
                    try {
                        Thread.sleep(5000)
                    }catch (e: InterruptedException){
                        e.printStackTrace()
                    }
                }
            }
        }).start()
    }

    override fun onCleared() {
        super.onCleared()
        isStarted = false
    }
}