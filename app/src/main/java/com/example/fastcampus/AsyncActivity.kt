package com.example.fastcampus

import android.os.AsyncTask
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
import kotlin.jvm.Throws

class AsyncActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_async_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val task = BackgroundAsyncTask(
            findViewById(R.id.progressBar),
            findViewById(R.id.progressBarText)
        )
        findViewById<TextView>(R.id.start).setOnClickListener{
            task.execute()
        }
        findViewById<TextView>(R.id.stop).setOnClickListener{
            task.cancel(true)
        }
    }
}
class BackgroundAsyncTask(
    val progressbar: ProgressBar,
    val progressText: TextView
):AsyncTask<Int,Int,Int>() {

    // Params, Progress, Result
    // params -> doInbackground에서 사용할 타입
    // profress -> onProgressUpdate에서 사용할 타입
    //result -> onPostExcute에서 사용할 타입
    var percent : Int = 0
    override fun doInBackground(vararg params: Int?): Int {
        while (isCancelled() == false){
            percent++
            if(percent>100) break
            else{
                publishProgress(percent)
            }
            Thread.sleep(100)
        }
        return percent
    }

    override fun onPreExecute() {
        percent = 0
        progressbar.setProgress(percent)
    }

    override fun onPostExecute(result: Int?) {
        progressText.text = "작업이 완료되었습니다."
    }

    override fun onProgressUpdate(vararg values: Int?) {
        progressbar.setProgress(values[0]?:0)
        progressText.text = "퍼센트 : " + values[0] + "%"
    }

    override fun onCancelled() {
        progressbar.setProgress(0)
        progressText.text = "작업이 취소되었습니다."
    }
}