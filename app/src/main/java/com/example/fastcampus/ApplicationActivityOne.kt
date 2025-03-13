package com.example.fastcampus

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ApplicationActivityOne : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_application_one)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<TextView>(R.id.changeActivity).setOnClickListener{
            startActivity(Intent(this, ApplicationActivityTwo::class.java))
        }
        findViewById<TextView>(R.id.testMethod).setOnClickListener{
            (applicationContext as MasterApplication).methodFromApplication()
            val userid = (applicationContext as MasterApplication).userId
            Log.d("testt", "user id is " + userid)
        }
    }
}