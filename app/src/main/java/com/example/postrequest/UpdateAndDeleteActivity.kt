package com.example.postrequest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.postrequest.API.APIClient
import com.example.postrequest.API.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateAndDeleteActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etLocation: EditText
    private lateinit var userPK: EditText
    private lateinit var updateButton: Button
    private lateinit var deleteButton: Button
    val apiClient = APIClient().getClient()?.create(APIInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_and_delete)

        etName = findViewById(R.id.etName)
        etLocation = findViewById(R.id.etLocation)
        userPK = findViewById(R.id.etId)
        updateButton = findViewById(R.id.updateButton)
        deleteButton = findViewById(R.id.deleteButton)

        updateButton.setOnClickListener {
            val  name = etName.text.toString()
            val location = etLocation.text.toString()
            val pk = userPK.text.toString().toInt()
            apiClient?.updateUser(pk, UserItem(name, location,pk ))?.enqueue(object :Callback<UserItem>{
                override fun onResponse(call: Call<UserItem>, response: Response<UserItem>) {
                    Toast.makeText(this@UpdateAndDeleteActivity,"User Updated Successfully", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@UpdateAndDeleteActivity,MainActivity::class.java)
                    startActivity(intent)
                }

                override fun onFailure(call: Call<UserItem>, t: Throwable) {
                    Toast.makeText(this@UpdateAndDeleteActivity,"Something went wrong ", Toast.LENGTH_LONG).show()
                }

            })

        }
        deleteButton.setOnClickListener {
            val intent = Intent(this@UpdateAndDeleteActivity,MainActivity::class.java)
            startActivity(intent)
        }
        deleteButton.setOnClickListener {
              val  name = etName.text.toString()
              val location = etLocation.text.toString()
              val pk = userPK.text.toString().toInt()
            deleteUser(pk)
        }

    }

    private fun deleteUser(pk:Int) {
        apiClient!!.deleteUser(pk).enqueue(object :Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Toast.makeText(this@UpdateAndDeleteActivity,"User Deleted Successfully",Toast.LENGTH_LONG).show()
                val intent = Intent(this@UpdateAndDeleteActivity,MainActivity::class.java)
                startActivity(intent)
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@UpdateAndDeleteActivity,"Something Went wrong",Toast.LENGTH_LONG).show()
            }

        })
    }

//    private fun updateUser() {
//        val  name = etName.text.toString()
//        val location = etLocation.text.toString()
//        val pk = userPK.text.toString().toInt()
//        apiClient?.updateUser(pk, UserItem(name, location,pk ))?.enqueue(object :Callback<UserItem>{
//            override fun onResponse(call: Call<UserItem>, response: Response<UserItem>) {
//                Toast.makeText(this@UpdateAndDeleteActivity,"User Updated Successfully", Toast.LENGTH_LONG).show()
//                val intent = Intent(this@UpdateAndDeleteActivity,MainActivity::class.java)
//                startActivity(intent)
//            }
//
//            override fun onFailure(call: Call<UserItem>, t: Throwable) {
//                Toast.makeText(this@UpdateAndDeleteActivity,"Something went wrong ", Toast.LENGTH_LONG).show()
//            }
//
//        })
//    }


}