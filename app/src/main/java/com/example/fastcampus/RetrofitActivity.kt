package com.example.fastcampus

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_retrofit)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = retrofit.getStudentList()
                    if (response.isSuccessful) {
                        val students = response.body()
                        withContext(Dispatchers.Main) {
                            students!!.forEach {
                                Log.d("RetrofitTest", "ID: ${it.id}, Name: ${it.name}, Age: ${it.age}")
                            }
                        }
                    }else{
                        Log.e("RetrofitTest", "Error Code: ${response.code()}")
                    }
                }catch (e: Exception){
                    e.printStackTrace()
                    Log.e("RetrofitTest", "Error: ${e.message}")
                }
            }
    }
}