package com.example.postrequest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.postrequest.API.APIClient
import com.example.postrequest.API.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
//    private lateinit var etName:EditText
//    private lateinit var etLocation:EditText
    private lateinit var addButton: Button
    private lateinit var updateDeleteButton:Button
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var userList: ArrayList<UserItem>

    val apiClient = APIClient().getClient()?.create(APIInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userList = arrayListOf()

        recyclerView = findViewById(R.id.recyclerView)


        getUser()

//        etName = findViewById(R.id.etName)
//        etLocation = findViewById(R.id.etLocation)
        addButton = findViewById(R.id.addButton)


        addButton.setOnClickListener {
//            val  name = etName.text.toString()
//            val location = etLocation.text.toString()
//            addUser(name,location,0)
           // getUser()
            val intent = Intent(this,AddUserActivity::class.java)
            startActivity(intent)
        }

        updateDeleteButton = findViewById(R.id.updateDeleteButton)
        updateDeleteButton.setOnClickListener {
            val intent = Intent(this,UpdateAndDeleteActivity::class.java)
            startActivity(intent)
        }
    }

    // get data from API
    private fun getUser() {
       apiClient?.getUsers()?.enqueue(object :Callback<User> {
           override fun onResponse(call: Call<User>, response: Response<User>) {

               val userList = response.body()!!
               recyclerView.adapter = RecyclerViewAdapter(userList)
               recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
               recyclerView.adapter?.notifyDataSetChanged()
           }
           override fun onFailure(call: Call<User>, t: Throwable) {
               Toast.makeText(applicationContext,"Something get data ",Toast.LENGTH_LONG).show()
           }
       })

    }

//     // add data from user to API
//    private fun addUser(name: String, location:String ,pk: Int){
//        apiClient?.postUsers(UserItem(name,location,0))?.enqueue(object : Callback<UserItem>{
//            override fun onResponse(call: Call<UserItem>, response: Response<UserItem>) {
//            Toast.makeText(this@MainActivity,"user added successfully",Toast.LENGTH_LONG).show()
//
//            }
//            override fun onFailure(call: Call<UserItem>, t: Throwable) {
//                Toast.makeText(this@MainActivity,"Something went wrong ",Toast.LENGTH_LONG).show()
//            }
//        })
//    }
//

}

