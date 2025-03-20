package com.example.fastcampus

import retrofit2.Response
import retrofit2.http.GET

data class StudentFromServer(
    val id: Int, val name: String, val age: Int, val intro: String
)
interface RetrofitService {
    @GET("json/students/")
    suspend fun getStudentList():Response<List<StudentFromServer>>
}