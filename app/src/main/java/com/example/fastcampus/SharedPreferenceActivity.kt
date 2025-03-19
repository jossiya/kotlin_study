package com.example.fastcampus

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SharedPreferenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_shared_preference)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<TextView>(R.id.create).setOnClickListener {
            //create
            val sharedPreference = getSharedPreferences("table_name", Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.putString("key1", "hello One")
            editor.putString("key2", "hello Two")
            editor.commit()
        }
        findViewById<TextView>(R.id.read).setOnClickListener {
            //read
            val sharedPreference = getSharedPreferences("table_name", Context.MODE_PRIVATE)
            val valueOne = sharedPreference.getString("key1", "Wrong1")
            val valueTwo = sharedPreference.getString("key2", "Wrong2")
            Log.d("testt", valueOne!!)
            Log.d("testt", valueTwo!!)
        }
        findViewById<TextView>(R.id.update).setOnClickListener {
            //update
            val sharedPreference = getSharedPreferences("table_name", Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.putString("key1", "hello hello")
            editor.commit()
        }
        findViewById<TextView>(R.id.delete).setOnClickListener {
            //update
            val sharedPreference = getSharedPreferences("table_name", Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.clear()
//            editor.remove("key1")
            editor.commit()
        }
    }
}