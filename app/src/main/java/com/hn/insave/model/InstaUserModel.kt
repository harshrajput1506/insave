package com.hn.insave.model

import android.os.Parcel
import android.os.Parcelable

class InstaUserModel() : Parcelable {
    var uid: Long = 0
    var name: String? = null
    var username: String? = null
    var image_url: String? = null
    var mcd: String? = null
    var mid: String? = null
    var csrftoken: String? = null
    var ds_user_id: String? = null
    var sessionid: String? = null
    var rur: String? = null
    var urlgen: String? = null
    var shbid: String? = null
    var shbts: String? = null
    var cookie: String? = null
    var isIs_login = false

    constructor(parcel: Parcel) : this() {
        uid = parcel.readLong()
        name = parcel.readString()
        username = parcel.readString()
        image_url = parcel.readString()
        mcd = parcel.readString()
        mid = parcel.readString()
        csrftoken = parcel.readString()
        ds_user_id = parcel.readString()
        sessionid = parcel.readString()
        rur = parcel.readString()
        urlgen = parcel.readString()
        shbid = parcel.readString()
        shbts = parcel.readString()
        cookie = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(uid)
        parcel.writeString(name)
        parcel.writeString(username)
        parcel.writeString(image_url)
        parcel.writeString(mcd)
        parcel.writeString(mid)
        parcel.writeString(csrftoken)
        parcel.writeString(ds_user_id)
        parcel.writeString(sessionid)
        parcel.writeString(rur)
        parcel.writeString(urlgen)
        parcel.writeString(shbid)
        parcel.writeString(shbts)
        parcel.writeString(cookie)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<InstaUserModel> {
        override fun createFromParcel(parcel: Parcel): InstaUserModel {
            return InstaUserModel(parcel)
        }

        override fun newArray(size: Int): Array<InstaUserModel?> {
            return arrayOfNulls(size)
        }
    }

}