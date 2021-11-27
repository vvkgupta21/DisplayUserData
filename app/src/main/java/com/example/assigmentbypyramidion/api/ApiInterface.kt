package com.example.assigmentbypyramidion.api

import com.example.assigmentbypyramidion.model.UserData
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://reqres.in/"

interface ApiInterface {

    @GET("api/users")
    fun getUserData(): Deferred<UserData>

}

//Retrofit Object,
//

object UserDataService{
    var userInstance :ApiInterface
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        userInstance = retrofit.create(ApiInterface::class.java)
    }
}