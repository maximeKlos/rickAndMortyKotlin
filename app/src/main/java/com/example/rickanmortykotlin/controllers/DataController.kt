package com.example.rickanmortykotlin.controllers

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.rickanmortykotlin.interfaces.MyCallback
import com.example.rickanmortykotlin.interfaces.RickAndMortyAPI
import com.example.rickanmortykotlin.model.Info
import com.example.rickanmortykotlin.model.ListCharacter
import com.example.rickanmortykotlin.model.Results
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DataController(characters: MutableList<Results>, call: MyCallback) : Callback<ListCharacter?> {

    val BASE_URL = "https://rickandmortyapi.com/api/"
    lateinit var infos: Info
    var characters: MutableList<Results>
    val call: MyCallback
    var gson: Gson
    var retrofit: Retrofit
    var rickAndMortyAPI: RickAndMortyAPI

    init {
        this.characters = characters
        this.call = call
        gson = GsonBuilder()
            .setLenient()
            .create()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        rickAndMortyAPI = retrofit.create<RickAndMortyAPI>(RickAndMortyAPI::class.java)
    }

    fun start(page: Int) {
        val call: Call<ListCharacter?>? = rickAndMortyAPI.getCharacters(page)
        call?.enqueue(this)
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    override fun onResponse(call: Call<ListCharacter?>, response: Response<ListCharacter?>) {
        val listCharacters: ListCharacter? = response.body()
        characters.clear()
        characters.addAll(listCharacters?.results!!)
        if (response.isSuccessful()) infos = listCharacters.info
        else println(response.errorBody())
        this.call.onWorkDone(1)
    }

    override fun onFailure(call: Call<ListCharacter?>, t: Throwable) {
        t.printStackTrace()
    }
}