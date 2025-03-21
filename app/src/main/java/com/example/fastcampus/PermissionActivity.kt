package com.example.fastcampus

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class PermissionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_permission)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<TextView>(R.id.askPermission).setOnClickListener{
            val cameraPermission = ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            )
            if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
                //권한이 없는 경우
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.CAMERA),
                    1000
                )
            }else{
                Log.d("Testt", "카메라 권한 있음")
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)
        if (requestCode == 1000) {
            // 우리가 보낸 권한 요청이 맙다면
            if (grantResults[0] === PackageManager.PERMISSION_GRANTED) {
                    Log.d("testt", "승낙")
            }else{
                Log.d("testt","승낙 거절")
            }
        }
    }
}