package com.example.androidolympiadsemifinal.view.adapter

import com.example.androidolympiadsemifinal.model.ServiceModel
import org.junit.Assert
import org.junit.Test

internal class RecyclerViewAdapterTest {
    @Test
    fun getItemCount() {
        val dataList = ServiceModel(listOf())
        Assert.assertEquals(0, dataList.items.size)
    }
}