package com.example.rickanmortykotlin.model

import com.google.gson.annotations.SerializedName

data class ListCharacter (

	@SerializedName("info") val info : Info,
	@SerializedName("results") val results : List<Results>
)