package com.example.androidolympiadsemifinal.network

import com.example.androidolympiadsemifinal.model.ServiceModel
import retrofit2.http.GET
import retrofit2.http.Query

interface VkProjectsApi {
    @GET(".semi-final-data.json")
    fun vkProjectsData(): ServiceModel
}