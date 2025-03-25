package com.example.fastcampus

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.io.Serializable

data class StudentFromServer(
    val id: Int, val name: String, val age: Int, val intro: String
){
    constructor( name: String, age: Int, intro: String):this(0, name, age, intro)
}
data class YoutubeItem(val id: Int, val title: String, val content: String, val video: String, val thumbnail: String)

data class MelonItem(
    val id: Int, val title: String, val song: String, val thumbnail: String
):Serializable
interface RetrofitService {
    @GET("melon/list/")
    suspend fun getMelonItemList(): Response<List<MelonItem>>

    @GET("json/students/")
    suspend fun getStudentList(): Response<List<StudentFromServer>>

    @POST("json/students/")
    suspend fun createStudent(@Body params: HashMap<String, Any>): Response<StudentFromServer>

    @POST("json/students/")
    suspend fun easyCreateStudent(@Body params: StudentFromServer): Response<StudentFromServer>

    @GET("youtube/list/")
    suspend fun getYoutubeItemList(): Response<ArrayList<YoutubeItem>>
}


