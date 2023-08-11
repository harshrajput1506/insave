package com.hn.insave.model

import android.os.Parcel
import android.os.Parcelable

class StoryModel() : Parcelable {
    var filePath: String? = null
    var fileName: String? = null
    var type = 0
    var saved: Boolean? = null

    constructor(parcel: Parcel) : this() {
        filePath = parcel.readString()
        fileName = parcel.readString()
        type = parcel.readInt()
        saved = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(filePath)
        parcel.writeString(fileName)
        parcel.writeInt(type)
        parcel.writeValue(saved)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StoryModel> {
        override fun createFromParcel(parcel: Parcel): StoryModel {
            return StoryModel(parcel)
        }

        override fun newArray(size: Int): Array<StoryModel?> {
            return arrayOfNulls(size)
        }
    }

}