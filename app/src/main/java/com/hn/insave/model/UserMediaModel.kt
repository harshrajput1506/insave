package com.hn.insave.model

import android.os.Parcel
import android.os.Parcelable

class UserMediaModel() : Parcelable {
    var mediaUrl: String? = null
    var displayUrl: String? = null
    var isVideo = false

    constructor(parcel: Parcel) : this() {
        mediaUrl = parcel.readString()
        displayUrl = parcel.readString()
        isVideo = parcel.readByte() != 0.toByte()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(mediaUrl)
        parcel.writeString(displayUrl)
        parcel.writeByte(if (isVideo) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserMediaModel> {
        override fun createFromParcel(parcel: Parcel): UserMediaModel {
            return UserMediaModel(parcel)
        }

        override fun newArray(size: Int): Array<UserMediaModel?> {
            return arrayOfNulls(size)
        }
    }

}