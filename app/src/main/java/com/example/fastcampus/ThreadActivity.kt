package com.example.fastcampus

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ThreadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_thread)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val currentThread = Thread.currentThread()
        Log.d("testt", ""+currentThread)
        Thread{
            val currentThread = Thread.currentThread()
            Log.d("testt", ""+currentThread)
            findViewById<TextView>(R.id.test).text = "changed"
            //UI관련 작업을 메인쓰레드가 아닌 쓰레드에서 하려ㅛ고 하면 해당 작업 메인쓰레드의 QUEUE로 들어간다
            //-> 에러가 발생하지 않을 수도 있다.

            runOnUiThread{
                findViewById<TextView>(R.id.test).text = "test"

            }
        }.start()
    }
}