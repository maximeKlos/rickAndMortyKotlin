package com.example.rickanmortykotlin.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.rickanmortykotlin.R
import com.example.rickanmortykotlin.model.Results
import coil.load

class DetailPersonnageActivity : AppCompatActivity() {

    lateinit var name: TextView
    lateinit var status: TextView
    lateinit var species: TextView
    lateinit var type: TextView
    lateinit var gender: TextView
    lateinit var origin: TextView
    lateinit var location: TextView
    lateinit var imageChar: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_personnage)

        name = findViewById(R.id.tvDetailName)
        status = findViewById(R.id.tvDetailStatus)
        species = findViewById(R.id.tvDetailSpecies)
        type = findViewById(R.id.tvDetailType)
        gender = findViewById(R.id.tvDetailGender)
        origin = findViewById(R.id.tvDetailOrigin)
        location = findViewById(R.id.tvDetailLocation)
        imageChar = findViewById(R.id.imageDetailChar)

        val character = intent.getParcelableExtra<Results>("personnage")

        imageChar.load(character?.image)
        name.text = character?.name
        val charStatus = character?.status
        when(charStatus){
            "Dead" -> status.setTextColor(Color.RED)
            "Alive" -> status.setTextColor(Color.GREEN)
            "unknown" -> status.setTextColor(Color.GRAY)
        }
        status.text = "•$charStatus"
        species.text = character?.species
        type.text = character?.type
        gender.text = character?.gender
        origin.text = character?.origin?.name
        location.text = character?.location?.name
    }
}