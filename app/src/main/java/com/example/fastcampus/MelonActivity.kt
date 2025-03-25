package com.example.fastcampus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable

class MelonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_melon)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(RetrofitService::class.java)
        CoroutineScope(Dispatchers.IO).launch{
            try {
                val response = retrofitService.getMelonItemList()
                if (response.isSuccessful) {
                    val melonItemList = response.body()
                    withContext(Dispatchers.Main){
                        findViewById<RecyclerView>(R.id.melonListView).apply {
                            this.adapter = MelonItemListAdapter(melonItemList!!,
                                LayoutInflater.from(this@MelonActivity),
                                Glide.with(this@MelonActivity),
                                this@MelonActivity
                            )
                        }
                    }

                }else{
                    Log.e("testt", "Error Code: ${response.code()}" )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("testt", "Error: ${e.message}")
            }
        }
    }
}
class MelonItemListAdapter(
    val melonItemList: List<MelonItem>,
    val inflater: LayoutInflater,
    val glide: RequestManager,
    val context : Context
): RecyclerView.Adapter<MelonItemListAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val title: TextView
        val thumbnail: ImageView
        val play: ImageView

        init{
            title = itemView.findViewById(R.id.title)
            thumbnail = itemView.findViewById(R.id.thumbnail)
            play = itemView.findViewById(R.id.play)
            play.setOnClickListener{

                val intent = Intent(context, MelonDetailActivity::class.java)

                intent.putExtra("melon_item_list",melonItemList as Serializable )
                intent.putExtra("position", adapterPosition)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.melon_item, parent, false))
    }

    override fun getItemCount(): Int {
        return melonItemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = melonItemList[position].title
        glide.load(melonItemList[position].thumbnail).centerCrop().into(holder.thumbnail)
    }

}