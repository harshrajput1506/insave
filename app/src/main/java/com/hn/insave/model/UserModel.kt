package com.hn.insave.model

import java.io.Serializable

class UserModel() : Serializable {
    var userName: String? = null
    var userId: String? = null
    var realName: String? = null
    var image: String? = null
    var isAdd: Boolean = false
    var isCustomDownload: Int = 0

    constructor(isAdd: Boolean) : this() {
        this.isAdd = isAdd
    }

    constructor(isCustomDownload: Int) : this() {
        this.isCustomDownload = isCustomDownload
    }
}