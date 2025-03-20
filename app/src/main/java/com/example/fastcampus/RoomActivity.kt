package com.example.fastcampus

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import org.w3c.dom.Text

class RoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_room)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //쓰레드를 이용하거나 async를 이용하면 된다.
        val database = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,
            "user-database"
        ).allowMainThreadQueries().build()
        findViewById<TextView>(R.id.save).setOnClickListener{
            val userProfile = UserProfile("홍","길동")
            database.userProfileDao().insert(userProfile)

        }
        findViewById<TextView>(R.id.load).setOnClickListener{
            val userProfiles = database.userProfileDao().getAll()
            userProfiles.forEach{
                Log.d("testt", "" + it.id+ it.firstName)
            }
            Log.d("testt", ""+ userProfiles)
        }
        findViewById<TextView>(R.id.delete).setOnClickListener{
            val userProfiles = database.userProfileDao().deleteById(1)
            Log.d("testt", "" + userProfiles)
        }
    }
}

@Database(entities = [UserProfile::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userProfileDao():UserProfileDao
}

@Dao
interface UserProfileDao {
    //CRUD
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userProFile: UserProfile)
    //DELETE
    @Query("DELETE FROM UserProfile WHERE id = :userId")
    fun deleteById(userId: Int)

    //SEARCH
    @Query(value = "SELECT * FROM UserProfile")
    fun getAll():List<UserProfile>
}
@Entity
class UserProfile(

    @PrimaryKey(autoGenerate = true) val id: Int,

    @ColumnInfo(name = "last_name")
    val lastName: String,

    @ColumnInfo(name = "first_name")
    val firstName: String
){
    constructor(lastName: String, firstName: String) : this(0, lastName, firstName)

}

