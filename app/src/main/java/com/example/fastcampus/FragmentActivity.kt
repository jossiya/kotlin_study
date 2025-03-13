package com.example.fastcampus

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fragment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val fragmentManager =supportFragmentManager
        val fragmentFirst = FragmentFirst()

        // Transaction
        // -  작업의 단위
        // A,B,C,D
        (findViewById<TextView>(R.id.add)).setOnClickListener{
            val transaction = fragmentManager.beginTransaction() //시작
            // 프래그먼트에 데이터를 전달 하는 방법
            val bundle = Bundle()
            bundle.putString("key", "hello")
            fragmentFirst.arguments = bundle


            transaction.replace(R.id.root, fragmentFirst,"fragment_first_tag")
            transaction.commit() // 끝
        }

        (findViewById<TextView>(R.id.remove)).setOnClickListener{
            val transaction = fragmentManager.beginTransaction()
            transaction.remove(fragmentFirst)
            transaction.commit()
        }

        (findViewById<TextView>(R.id.access_fragment)).setOnClickListener{
            // XML 에 있는 Fragment
//            val fragmentFirst = supportFragmentManager.findFragmentById(R.id.fragment_first) as FragmentFirst
            //
           val fragmentFirst= supportFragmentManager.findFragmentByTag("fragment_first_tag") as FragmentFirst
            fragmentFirst.printTestLog()
        }
    }

    fun printTestLog(){
        Log.d("testt", "print test log")
    }
}