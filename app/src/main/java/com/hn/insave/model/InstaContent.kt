package com.hn.insave.model

import android.os.Parcel
import android.os.Parcelable

class InstaContent() :Parcelable {
    var url: String? = null
    var videoThumbnailUrl: String? = null
    var isVideo: Boolean = false
    private var isSelected = true

    constructor(parcel: Parcel) : this() {
        url = parcel.readString()
        videoThumbnailUrl = parcel.readString()
        isVideo = parcel.readByte() != 0.toByte()
        isSelected = parcel.readByte() != 0.toByte()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(url)
        parcel.writeString(videoThumbnailUrl)
        parcel.writeByte(if (isVideo) 1 else 0)
        parcel.writeByte(if (isSelected) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<InstaContent> {
        override fun createFromParcel(parcel: Parcel): InstaContent {
            return InstaContent(parcel)
        }

        override fun newArray(size: Int): Array<InstaContent?> {
            return arrayOfNulls(size)
        }
    }

}