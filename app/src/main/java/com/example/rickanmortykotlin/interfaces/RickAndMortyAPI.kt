package com.example.rickanmortykotlin.interfaces

import com.example.rickanmortykotlin.model.ListCharacter
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface RickAndMortyAPI {
    @GET("character")
    fun getCharacters(@Query("page") page: Int): Call<ListCharacter?>?
}