package com.example.androidolympiadsemifinal.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidolympiadsemifinal.R

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