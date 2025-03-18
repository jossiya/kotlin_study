package com.example.fastcampus

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class ListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var carList = mutableListOf<Car>()
        
        //데이터 준비
        for (i in 0..100) {
            carList.add(Car("" + i + "번쨰 자동차", "" + i + "번째 엔진"))
        }
        //어답터 준비
        val adapter = ListViewAdapter(
            carList,
            LayoutInflater.from(this),
            this
        )

        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = adapter
    }
}

class ListViewAdapter(
    val carList: MutableList<Car>,
    val layoutInflater: LayoutInflater,
    val context: Context
) : BaseAdapter() {
    override fun getCount(): Int {
        return carList.size
    }

    override fun getItem(position: Int): Any {
    return carList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = layoutInflater.inflate(R.layout.car_item,null)
        val carImage = view.findViewById<ImageView>(R.id.carImage)
        val nthCar = view.findViewById<TextView>(R.id.nthCar)
        val nthEngine = view.findViewById<TextView>(R.id.nthEngine)
        val car = carList[position]
        carImage.setImageDrawable(context.resources.getDrawable(R.drawable.blue_background,context.theme))
        nthCar.text = car.nthCar
        nthEngine.text = car.nthEngine
        return view
    }

}