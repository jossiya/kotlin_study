package com.example.fastcampus

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityThree : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_three)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        (findViewById<TextView>(R.id.one)).setOnClickListener{
            val intent = Intent(this@ActivityThree, ActivityOne::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
        (findViewById<TextView>(R.id.two)).setOnClickListener{
            startActivity(Intent(this@ActivityThree, ActivityTwo::class.java))
        }
        (findViewById<TextView>(R.id.three)).setOnClickListener{
            startActivity(Intent(this@ActivityThree, ActivityThree::class.java))
        }
    }
}