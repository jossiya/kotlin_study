package com.example.fastcampus

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityTwo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_two)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        (findViewById<TextView>(R.id.one)).setOnClickListener{
            startActivity(Intent(this@ActivityTwo, ActivityOne::class.java))
        }
        (findViewById<TextView>(R.id.two)).setOnClickListener{
            startActivity(Intent(this@ActivityTwo, ActivityTwo::class.java))
        }
        (findViewById<TextView>(R.id.three)).setOnClickListener{
            startActivity(Intent(this@ActivityTwo, ActivityThree::class.java))
        }
    }
}