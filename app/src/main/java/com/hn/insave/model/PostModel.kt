package com.hn.insave.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class PostModel : Parcelable {
    var profileUrl: String? = null
    var fullName: String? = null
    var username: String? = null
    var caption: String? = null
    var totalLikes: String? = null
    var totalComments: String? = null
    var instaContent: List<InstaContent>? = ArrayList()

    constructor() {}
    constructor(parcel: Parcel) {
        profileUrl = parcel.readString()
        fullName = parcel.readString()
        username = parcel.readString()
        caption = parcel.readString()
        totalLikes = parcel.readString()
        totalComments = parcel.readString()
        instaContent = parcel.createTypedArrayList(InstaContent)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(profileUrl)
        parcel.writeString(fullName)
        parcel.writeString(username)
        parcel.writeString(caption)
        parcel.writeString(totalLikes)
        parcel.writeString(totalComments)
        parcel.writeTypedList(instaContent)
    }

    companion object {
        val CREATOR: Parcelable.Creator<PostModel?> = object : Parcelable.Creator<PostModel?> {
            override fun createFromParcel(parcel: Parcel): PostModel {
                return PostModel(parcel)
            }

            override fun newArray(size: Int): Array<PostModel?> {
                return arrayOfNulls(size)
            }
        }
    }
}
