package com.example.rickanmortykotlin.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Results (

	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("status") val status : String,
	@SerializedName("species") val species : String,
	@SerializedName("type") val type : String,
	@SerializedName("gender") val gender : String,
	@SerializedName("origin") val origin : Origin,
	@SerializedName("location") val location : Location,
	@SerializedName("image") val image : String,
	@SerializedName("episode") val episode : List<String>,
	@SerializedName("url") val url : String,
	@SerializedName("created") val created : String
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readInt(),
		parcel.readString()?: String(),
		parcel.readString()?: String(),
		parcel.readString()?: String(),
		parcel.readString()?: String(),
		parcel.readString()?: String(),
		parcel.readParcelable<Origin>(Origin::class.java.classLoader) as Origin,
		parcel.readParcelable<Location>(Location::class.java.classLoader) as Location,
		parcel.readString()?: String(),
		parcel.createStringArrayList()?: emptyList(),
		parcel.readString()?: String(),
		parcel.readString()?: String()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeInt(id)
		parcel.writeString(name)
		parcel.writeString(status)
		parcel.writeString(species)
		parcel.writeString(type)
		parcel.writeString(gender)
		parcel.writeParcelable(origin, flags)
		parcel.writeParcelable(location, flags)
		parcel.writeString(image)
		parcel.writeStringList(episode)
		parcel.writeString(url)
		parcel.writeString(created)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Results> {
		override fun createFromParcel(parcel: Parcel): Results {
			return Results(parcel)
		}

		override fun newArray(size: Int): Array<Results?> {
			return arrayOfNulls(size)
		}
	}
}