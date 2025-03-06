package com.example.fastcampus

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Activity_01 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_01)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.d("LifeCycle", "onCreate")
    }

    override fun onStart() {
        Log.d("LifeCycle", "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("LifeCycle", "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("LifeCycle", "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("LifeCycle", "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("LifeCycle", "onDestroy")
        super.onDestroy()
    }

    override fun onRestart() {
        Log.d("LifeCycle", "onRestart")
        super.onRestart()
    }
}