package com.example.fastcampus

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged

class Homework_2 : AppCompatActivity() {
    private var finalUrl: String = "https://www.google.com" // 기본 URL 설정
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_homework2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val webView = findViewById<WebView>(R.id.webView)
        webView.apply {
            webViewClient = object :WebViewClient(){
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    return false
                }
            }

        }

        try {
        webView.loadUrl(intent.data?.toString() ?: finalUrl)
        } catch (exception: Exception) {
            Log.d("ex : ",exception.message.toString())
        }
        val address = findViewById<EditText>(R.id.address)
        address.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d("testt", "beforeTextChanged : " + s)

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("testt", "onTextChanged : " + s)
            }

            override fun afterTextChanged(s: Editable?) {
                var url = s.toString()
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    finalUrl = "https://$url"
                } else {
                    finalUrl = url
                }
                Log.d("testt", "afterTextChanged : " + s)
            }
        })
        // 람다 방식
        address.doAfterTextChanged { }

        val open = findViewById<TextView>(R.id.open)
        open.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(finalUrl))
            startActivity(intent)

//            webView.loadUrl(finalUrl)
        }
    }
}