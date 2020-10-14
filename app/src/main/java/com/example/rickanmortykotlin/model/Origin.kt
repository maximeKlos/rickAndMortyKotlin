package com.example.rickanmortykotlin.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Origin (

	@SerializedName("name") val name : String,
	@SerializedName("url") val url : String
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString()?: String(),
		parcel.readString()?: String()
	) {
	}

	override fun writeToParcel(parcel: Parcel, flags: Int) {
		parcel.writeString(name)
		parcel.writeString(url)
	}

	override fun describeContents(): Int {
		return 0
	}

	companion object CREATOR : Parcelable.Creator<Origin> {
		override fun createFromParcel(parcel: Parcel): Origin {
			return Origin(parcel)
		}

		override fun newArray(size: Int): Array<Origin?> {
			return arrayOfNulls(size)
		}
	}
}