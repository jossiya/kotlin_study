package com.example.fastcampus

import android.content.res.Resources
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResourceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resource)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<TextView>(R.id.text).setOnClickListener{
            (it as TextView).text= resources.getText(R.string.app_name)
//            it.background= resources.getDrawable(R.drawable.arrows)// deprecated


//            it.background= resources.getDrawable(R.drawable.arrows,null)
//            it.background = ContextCompat.getDrawable(this, R.drawable.arrows)
            it.background = ResourcesCompat.getDrawable(resources,R.drawable.arrows,null)
        }
        findViewById<ImageView>(R.id.image).setOnClickListener{
            (it as ImageView).setImageDrawable(resources.getDrawable(R.drawable.logo_fb2,this.theme))
        }
    }
}