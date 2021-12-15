package com.example.postrequest.API

import com.example.postrequest.User
import com.example.postrequest.UserItem
import retrofit2.Call
import retrofit2.http.*


interface APIInterface {

    @GET("/test/")
    fun getUsers():retrofit2.Call<User>


    @POST("/test/")
    fun postUsers(@Body user: UserItem):retrofit2.Call<UserItem>

    @PUT("/test/{pk}")
    fun updateUser(@Path("pk") pk:Int , @Body user: UserItem): Call<UserItem>

    @DELETE("/test/{pk}")

    fun deleteUser(@Path("pk")pk: Int):Call<Void>
}