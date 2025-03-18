package com.example.fastcampus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class RecyclerViewHomework : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycler_view_homework)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val chatList = mutableListOf<Chat>()
        chatList.add(Chat("안녕하새요", false))
        chatList.add(Chat("잘지내니?", false))
        chatList.add(Chat("안녕하새요", true))
        chatList.add(Chat("응, 그래", false))
        chatList.add(Chat("잘 지내냐고", false))
        chatList.add(Chat("네 잘지냅니다", true))
        chatList.add(Chat("그래 수고하고", false))
        chatList.add(Chat("네 수고하세요", true))
        chatList.add(Chat("잘가고", false))
        chatList.add(Chat("안녕히가세요", true))
        chatList.add(Chat("빠이", false))

        val recyclerView = findViewById<RecyclerView>(R.id.chatRecyclerView)
        recyclerView.adapter = ChatRecyclerAdapter(chatList,LayoutInflater.from(this))
        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
        findViewById<Button>(R.id.button).setOnClickListener{
            (recyclerView.adapter as ChatRecyclerAdapter).chatList.add(2,Chat("두번쨰 입니다.", false))
            (recyclerView.adapter as ChatRecyclerAdapter).notifyItemInserted(2)
        }
    }
}
class ChatRecyclerAdapter(
    var chatList: MutableList<Chat>,
    private var inflater: LayoutInflater,
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView: TextView
        init {
                textView = itemView.findViewById(R.id.chatText)
        }
    }
    override fun getItemViewType(position: Int): Int {
        return when(chatList[position].isRight) {
            true -> 1
            false -> 0
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = when(viewType) {
            1 ->inflater.inflate(R.layout.chatitem_right, parent, false)
            else -> inflater.inflate(R.layout.chatitem_left, parent, false)
        }
        return ViewHolder(view);
    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chat = chatList[position]
        (holder as ViewHolder).textView.text = chat.message
    }
}
class Chat(val message: String, val isRight: Boolean)