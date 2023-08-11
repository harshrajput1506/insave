package com.hn.insave.model

import android.os.Parcel
import android.os.Parcelable

class HistoryFilesModel() : Parcelable {
    var filename: String? = null
    var filePath: String? = null
    var username: String? = null
    var isVideo = false
    var timestamp: Long = 0

    constructor(parcel: Parcel) : this() {
        filename = parcel.readString()
        filePath = parcel.readString()
        username = parcel.readString()
        isVideo = parcel.readByte() != 0.toByte()
        timestamp = parcel.readLong()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(filename)
        parcel.writeString(filePath)
        parcel.writeString(username)
        parcel.writeByte(if (isVideo) 1 else 0)
        parcel.writeLong(timestamp)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HistoryFilesModel> {
        override fun createFromParcel(parcel: Parcel): HistoryFilesModel {
            return HistoryFilesModel(parcel)
        }

        override fun newArray(size: Int): Array<HistoryFilesModel?> {
            return arrayOfNulls(size)
        }
    }

}