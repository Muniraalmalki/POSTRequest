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

class AddUserActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etLocation: EditText
    private lateinit var userPK: EditText
    private lateinit var addButton: Button

    val apiClient = APIClient().getClient()?.create(APIInterface::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        addButton = findViewById(R.id.addButton)
        etName = findViewById(R.id.etName)
        etLocation = findViewById(R.id.etLocation)

        addButton.setOnClickListener {
            val name = etName.text.toString()
            val location = etLocation.text.toString()
            apiClient?.postUsers(UserItem(name, location, 0))?.enqueue(object : Callback<UserItem> {
                override fun onResponse(call: Call<UserItem>, response: Response<UserItem>) {
                    Toast.makeText(
                        this@AddUserActivity,
                        "user added successfully",
                        Toast.LENGTH_LONG
                    ).show()
                    val intent = Intent(this@AddUserActivity, MainActivity::class.java)
                    startActivity(intent)

                }

                override fun onFailure(call: Call<UserItem>, t: Throwable) {
                    Toast.makeText(this@AddUserActivity, "Something went wrong ", Toast.LENGTH_LONG)
                        .show()
                }
            })

        }
    }
}