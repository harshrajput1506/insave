package com.hn.insave.model

class UserDetailModel {
    var userName: String? = null
    var profilePic: String? = null
    var caption: String? = null
    var totalLikes = "0"
    var totalComments = "0"
    var userMediaModelList: List<UserMediaModel>? = null
}