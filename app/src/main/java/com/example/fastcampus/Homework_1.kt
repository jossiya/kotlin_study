package com.example.fastcampus

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Homework_1 : AppCompatActivity() {

    lateinit var one: TextView
    lateinit var two: TextView
    lateinit var three: TextView
    lateinit var four: TextView
    lateinit var five: TextView
    lateinit var six: TextView
    lateinit var seven: TextView
    lateinit var eight: TextView
    lateinit var nine: TextView
    lateinit var zero: TextView
    lateinit var ca: TextView
    lateinit var plus: TextView
    lateinit var equal: TextView
    lateinit var result: TextView

    var input: String = ""
    var temp: Int = 0
    var isNewInput: Boolean = false // 새로운 입력이 시작되었는지 확인하는 플래그

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_homwork1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViews()
        setNumberTextViewListener()

        // **초기화 버튼 (CA)**
        ca.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                input = ""
                temp = 0
                result.text = ""
                isNewInput = false
            }
        })

        // **더하기 버튼 (+)**
        plus.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (input.isNotEmpty()) {
                    temp += input.toInt() // 기존 temp에 현재 입력값을 더함
                    result.text = temp.toString() // 화면에 현재까지의 결과 표시
                    input = "" // 새로운 입력을 받을 준비
                    isNewInput = true // 새로운 입력 시작
                }
            }
        })

        // **계산 결과 버튼 (=)**
        equal.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (input.isNotEmpty()) {
                    temp += input.toInt() // 마지막으로 입력된 숫자를 temp에 더함
                    result.text = temp.toString()
                    input = temp.toString() // 연속 연산을 위해 input에 저장
                    isNewInput = true // 새로운 입력 시작
                }
            }
        })
    }

    // **숫자 버튼 이벤트 처리**
    fun setNumberTextViewListener() {
        val numberTextViewList: List<TextView> = listOf(
            one, two, three, four, five, six, seven, eight, nine, zero
        )

        val listener = object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (isNewInput) {
                    input = "" // 새로운 입력이 시작되면 기존 값을 초기화
                    isNewInput = false
                }
                input += (v as TextView).text
                result.text = input
            }
        }

        numberTextViewList.forEach {
            it.setOnClickListener(listener)
        }
    }

    // **뷰 바인딩**
    fun findViews() {
        one = findViewById(R.id.one)
        two = findViewById(R.id.two)
        three = findViewById(R.id.three)
        four = findViewById(R.id.four)
        five = findViewById(R.id.five)
        six = findViewById(R.id.six)
        seven = findViewById(R.id.seven)
        eight = findViewById(R.id.eight)
        nine = findViewById(R.id.nine)
        zero = findViewById(R.id.zero)
        ca = findViewById(R.id.ca)
        plus = findViewById(R.id.plus)
        equal = findViewById(R.id.equal)
        result = findViewById(R.id.result)
    }
}
