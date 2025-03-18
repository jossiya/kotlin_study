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
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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
        //어답터 장착 방법
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = adapter

        //리스너 장착 방법
        listView.setOnItemClickListener { parent, view, position, id ->
            val car : Car = adapter.carList[position]
            val nthCar = car.nthCar
            val nthEngine  = car.nthEngine

            Toast.makeText(this, nthCar + "  " + nthEngine, Toast.LENGTH_LONG).show()
        }

        //데이터 갱신 방
        findViewById<TextView>(R.id.addCar).setOnClickListener{
            adapter.carList.add(Car("안녕 나는 차", "안녕 나는 엔진"))
            adapter.notifyDataSetChanged()
        }

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

        val view : View
        val holder : ViewHolder

        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.car_item,null)
            holder = ViewHolder()
            holder.carImage = view.findViewById(R.id.carImage)
            holder.nthCar = view.findViewById(R.id.nthCar)
            holder.nthEngine = view.findViewById(R.id.nthEngine)

            view.tag = holder
        }else{

            holder =convertView.tag as ViewHolder
            view = convertView
        }
        holder.carImage?.setImageDrawable(context.resources.getDrawable(R.drawable.blue_background,context.theme))
        val car = carList[position]
        holder.nthCar?.text = car.nthCar
        holder.nthEngine?.text = car.nthEngine
        return view
        // 해당 번째 뷰를 리턴
//        val view = layoutInflater.inflate(R.layout.car_item,null)
//        val carImage = view.findViewById<ImageView>(R.id.carImage)
//        val nthCar = view.findViewById<TextView>(R.id.nthCar)
//        val nthEngine = view.findViewById<TextView>(R.id.nthEngine)
//        val car = carList[position]
//        carImage.setImageDrawable(context.resources.getDrawable(R.drawable.blue_background,context.theme))
//        nthCar.text = car.nthCar
//        nthEngine.text = car.nthEngine
        return view
    }

}
class ViewHolder(
    var carImage: ImageView? = null,
    var nthCar: TextView? = null,
    var nthEngine: TextView? = null
)