package com.example.fastcampus

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import retrofit2.http.Url
import java.io.Serializable

class MelonDetailActivity : AppCompatActivity() {
    lateinit var playPauseButton : ImageView
    var isPlaying:Boolean = true
        set(value) {
            if (value == true) {
                playPauseButton.setImageDrawable(this.resources.getDrawable(R.drawable.pause,this.theme))
            }else{
                playPauseButton.setImageDrawable(this.resources.getDrawable(R.drawable.play,this.theme))
            }
            field = value
        }
    lateinit var melonItemList: ArrayList<MelonItem>
    lateinit var mediaPlayer: MediaPlayer
    var position = 0
        set(value) {
            if(value<=0) field =0
            else if(value>= melonItemList.size) field = melonItemList.size
            else field = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_melon_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            melonItemList =
                (intent.getSerializableExtra("melon_item_list", ArrayList::class.java) as? ArrayList<MelonItem>)!!
            position = intent.getIntExtra("position",0)
            mediaPlayer= MediaPlayer.create(this, Uri.parse(melonItemList!!.get(position).song ))
            mediaPlayer.start()
        } else {
            @Suppress("DEPRECATION")
            melonItemList = (intent.getSerializableExtra("melon_item_list") as? ArrayList<MelonItem>)!!
            position = intent.getIntExtra("position",0)
            mediaPlayer= MediaPlayer.create(this, Uri.parse(melonItemList!!.get(position).song ))
        }
        playPauseButton = findViewById(R.id.play)
        playPauseButton.setOnClickListener{
            if (isPlaying == true) {
                isPlaying=false
                mediaPlayer.stop()
            }else{
                isPlaying = true
                playMelonItem(melonItemList[position])
            }
        }
        findViewById<ImageView>(R.id.back).setOnClickListener{
            mediaPlayer.stop()
            position -= 1
            playMelonItem(melonItemList[position])
            changeThumbnail(melonItemList[position])
        }
        findViewById<ImageView>(R.id.next).setOnClickListener{
            mediaPlayer.stop()
            position += 1
            playMelonItem(melonItemList[position])
            changeThumbnail(melonItemList[position])
        }
    }
    fun playMelonItem(melonItem: MelonItem){
        mediaPlayer= MediaPlayer.create(this, Uri.parse(melonItem.song ))
        mediaPlayer.start()
    }
    fun changeThumbnail(melonItem: MelonItem){
        findViewById<ImageView>(R.id.thumbnail).apply {
            val glide = Glide.with(this@MelonDetailActivity)
            glide.load(melonItem).into(this)
        }

    }
}

