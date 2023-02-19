package com.example.androidolympiadsemifinal.network

import com.example.androidolympiadsemifinal.model.ServiceModel
import retrofit2.Call
import retrofit2.http.GET

interface VkProjectsApi {
    @GET("/semi-final-data.json")
    fun getProjectsData(): Call<ServiceModel>
}