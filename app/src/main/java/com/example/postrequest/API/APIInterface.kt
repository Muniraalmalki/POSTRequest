package com.example.postrequest.API

import com.example.postrequest.User
import com.example.postrequest.UserItem
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface APIInterface {

    @GET("/test/")
    fun getUsers():retrofit2.Call<User>


    @POST("/test/")
    fun postUsers(@Body user: UserItem):retrofit2.Call<UserItem>
}