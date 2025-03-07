package com.example.fastcampus

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ViewControl_02 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_control02)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // 뷰를 코틀린 파일(Activity)로 가져오는 방법
        val textViewOne : TextView = findViewById(R.id.textViewOne)
        val buttonOne : Button = findViewById(R.id.buttonOne)
        Log.d("testt", textViewOne.text.toString())

        //Listener 사용방법
        buttonOne.setOnClickListener{
            // 버튼ㅇ이 클리되었을 때 동작할 코드
            Log.d("testt", "버튼 클릭!!")
        }

        //풀버전
        // 익명 클레스
        val clickListener = object : View.OnClickListener{
            override fun onClick(v: View?) {
                Log.d("testt", "버튼 클릭!!")
            }
        }
        buttonOne.setOnClickListener(clickListener)
    }
}