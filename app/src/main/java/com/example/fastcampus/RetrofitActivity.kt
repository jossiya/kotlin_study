package com.example.fastcampus

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory

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
        val retrofit = Builder()
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
                        findViewById<RecyclerView>(R.id.studentsRecyclerview).apply {
                            this.adapter = StudentListRecyclerViewAdapter(
                                students!!,
                                LayoutInflater.from(this@RetrofitActivity)
                            )
                            this.layoutManager = LinearLayoutManager(this@RetrofitActivity)
                        }
                    }
                } else {
                    Log.e("RetrofitTest", "Error Code: ${response.code()}")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("RetrofitTest", "Error: ${e.message}")
            }
        }
        findViewById<TextView>(R.id.studentCreate).setOnClickListener {
            val student = HashMap<String, Any>()
            student["name"] = "조철호"
            student["age"] = 40
            student["intro"] = "코틀린 빡세요"
            CoroutineScope(Dispatchers.IO).launch {
                val response = retrofit.createStudent(student)
                try {
                    if (response.isSuccessful) {
                        withContext(Dispatchers.Main) {
                            Log.e("RetrofitTest", "성공: ${response.body()}")
                        }
                    } else {
                        Log.e("RetrofitTest", "Error Code: ${response.code()}")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.e("RetrofitTest", "Error: ${e.message}")
                }
            }
        }
        findViewById<TextView>(R.id.easyStudentCreate).setOnClickListener {
            val student = StudentFromServer(name = "하나", age = 100, "hello kotlin")
            CoroutineScope(Dispatchers.IO).launch {
                val response = retrofit.easyCreateStudent(student)
                try {
                    if (response.isSuccessful) {
                        withContext(Dispatchers.Main) {
                            Log.e("RetrofitTest", "성공: ${response.body()}")
                        }
                    } else {
                        Log.e("RetrofitTest", "Error Code: ${response.code()}")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.e("RetrofitTest", "Error: ${e.message}")
                }
            }
        }

    }
}

class StudentListRecyclerViewAdapter(
    var studentList: List<StudentFromServer>,
    var inflater: LayoutInflater
) : RecyclerView.Adapter<StudentListRecyclerViewAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val studentName: TextView
        val studentAge: TextView
        val studentIntro: TextView

        init {
            studentName = itemView.findViewById(R.id.studentName)
            studentAge = itemView.findViewById(R.id.studentAge)
            studentIntro = itemView.findViewById(R.id.studentIntro)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.student_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.studentName.text = studentList[position].name
        holder.studentAge.text = studentList[position].age.toString()
        holder.studentIntro.text = studentList[position].intro
    }


    override fun getItemCount(): Int {
        return studentList.size
    }


}