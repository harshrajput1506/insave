package com.hn.insave.utils

import android.util.Log
import com.hn.insave.model.InstaUserModel
import com.hn.insave.model.UserDetailModel
import com.hn.insave.model.UserMediaModel
import com.hn.insave.model.UserModel
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class SavedPreferences {
    fun parsingInstaUserModel(response: JSONObject): InstaUserModel {
        val instaUserModel = InstaUserModel()
        try {
            if (response.has("user")) {
                val userObject = response.getJSONObject("user")
                val username = "username"
                val profilePic = "profile_pic_url"
                if (userObject.has(username)) instaUserModel.username = userObject.getString(username)
                if (userObject.has("full_name")) instaUserModel.name = userObject.getString("full_name")
                if (userObject.has(profilePic)) instaUserModel.image_url = userObject.getString(profilePic)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return instaUserModel
    }

    fun parsingUserObject(ownerObject: JSONObject): UserModel {
        val model = UserModel()
        try {
            model.image = ownerObject["profile_pic_url"].toString()
            model.realName = ownerObject["full_name"].toString()
            model.userName = ownerObject["username"].toString()
            model.userId = ownerObject["pk"].toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return model
    }

    fun parsingUserProfilePic(response: JSONObject): UserDetailModel {
        val userDetailModel = UserDetailModel()
        try {
            val userObject = response.getJSONObject("user")
            if (userObject.has("profile_pic_url_hd") && !userObject.isNull("profile_pic_url_hd")) {
                val userMediaModelList: MutableList<UserMediaModel> = ArrayList<UserMediaModel>()
                val userMediaModel = UserMediaModel()
                userMediaModel.isVideo = false
                userMediaModel.displayUrl = userObject.getString("profile_pic_url_hd")
                userMediaModel.mediaUrl = userObject.getString("profile_pic_url_hd")
                userMediaModelList.add(userMediaModel)
                userDetailModel.userMediaModelList = userMediaModelList
                userDetailModel.profilePic = userObject.getString("profile_pic_url_hd")
            }
            if (userObject.has("username") && !userObject.isNull("username")) {
                userDetailModel.userName = userObject.getString("username")
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return userDetailModel
    }

    fun parsingPrivateVariable(response: JSONObject): Boolean {
        var isPrivateAccount = false
        try {
            if (response.has("shortcode_media") && !response.isNull("shortcode_media")) {
                val shortcode = response.getJSONObject("shortcode_media")
                if (shortcode.has("owner") && !shortcode.isNull("owner")) {
                    val ownerObject = shortcode.getJSONObject("owner")
                    if (ownerObject.has("is_private") && !ownerObject.isNull("is_private")) {
                        isPrivateAccount = ownerObject.getBoolean("is_private")
                    }
                } else {
                    Log.d("Private Account", "shortcode_media Not")
                }
            } else {
                Log.d("Private Account", "shortcode_media Not")
            }
        } catch (e: java.lang.Exception) {
            Log.d("Private Account", "Checking Private Account Or Not")
            e.printStackTrace()
        }
        return isPrivateAccount
    }

    fun parsingFollowByUserOrNot(response: JSONObject): Boolean {
        var isFollow = true
        try {
            if (response.has("shortcode_media") && !response.isNull("shortcode_media")) {
                val shortcode = response.getJSONObject("shortcode_media")
                if (shortcode.has("owner") && !shortcode.isNull("owner")) {
                    val ownerObject = shortcode.getJSONObject("owner")
                    if (ownerObject.has("is_private") && !ownerObject.isNull("followed_by_viewer")) isFollow = ownerObject.getBoolean("followed_by_viewer")
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return isFollow
    }

    fun parsingMediaFromLink(response: JSONObject): UserDetailModel? {
        Log.d("SavedPref", response.toString())
        val userDetailModel = UserDetailModel()
        val userMediaModelList: MutableList<UserMediaModel> = ArrayList()
        try {
            val shortCode_media_object = response.getJSONObject("shortcode_media")
            val caption = getCaption(shortCode_media_object)
            userDetailModel.caption = caption
            val likes = getLikes(shortCode_media_object)
            userDetailModel.totalLikes = likes.toString()
            val comments = getComments(shortCode_media_object)
            userDetailModel.totalComments = comments.toString()
            if (shortCode_media_object.has("edge_sidecar_to_children")) {
                val sideCarObject = shortCode_media_object.getJSONObject("edge_sidecar_to_children")
                val edgesArray = sideCarObject.getJSONArray("edges")
                for (i in 0 until edgesArray.length()) {
                    val edgeObject = edgesArray.getJSONObject(i)
                    val nodeObject = edgeObject.getJSONObject("node")
                    if (nodeObject.has("is_video")) {
                        val is_inner_video = nodeObject.getBoolean("is_video")
                        if (is_inner_video) {
                            if (nodeObject.has("video_url")) {
                                val mediaModel = UserMediaModel()
                                mediaModel.isVideo = true
                                mediaModel.displayUrl = nodeObject.getString("display_url")
                                mediaModel.mediaUrl = nodeObject.getString("video_url")
                                userMediaModelList.add(mediaModel)
                            }
                        } else {
                            if (nodeObject.has("display_url")) {
                                val mediaModel = UserMediaModel()
                                mediaModel.isVideo = false
                                mediaModel.displayUrl = nodeObject.getString("display_url")
                                mediaModel.mediaUrl = nodeObject.getString("display_url")
                                userMediaModelList.add(mediaModel)
                            }
                        }
                    }
                }
                userDetailModel.userMediaModelList = userMediaModelList
            } else {
                if (shortCode_media_object.has("is_video")) {
                    val is_video = shortCode_media_object.getBoolean("is_video")
                    if (is_video) {
                        if (shortCode_media_object.has("video_url")) {
                            val mediaModel = UserMediaModel()
                            mediaModel.isVideo = true
                            mediaModel.displayUrl = shortCode_media_object.getString("display_url")
                            mediaModel.mediaUrl = shortCode_media_object.getString("video_url")
                            userMediaModelList.add(mediaModel)
                        }
                    } else {
                        if (shortCode_media_object.has("display_url")) {
                            val mediaModel = UserMediaModel()
                            mediaModel.isVideo = false
                            mediaModel.displayUrl = shortCode_media_object.getString("display_url")
                            mediaModel.mediaUrl = shortCode_media_object.getString("display_url")
                            userMediaModelList.add(mediaModel)
                        }
                    }
                    userDetailModel.userMediaModelList = userMediaModelList
                }
            }
            Log.d("LIST SIZE ===", userMediaModelList.size.toString() + "")
            if (shortCode_media_object.has("owner") && !shortCode_media_object.isNull("owner")) {
                val ownerObject = shortCode_media_object.getJSONObject("owner")
                if (ownerObject.has("username") && !ownerObject.isNull("username")) {
                    userDetailModel.userName = ownerObject.getString("username")
                }
                if (ownerObject.has("profile_pic_url") && !ownerObject.isNull("profile_pic_url")) {
                    userDetailModel.profilePic = ownerObject.getString("profile_pic_url")
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return userDetailModel
    }

    private fun getLikes(shortCode_media_object: JSONObject): String? {
        try {
            if (!shortCode_media_object.has("edge_media_preview_like")) {
                return null
            }
            val edgeMediaLike = shortCode_media_object.getJSONObject("edge_media_preview_like")
            return if (!edgeMediaLike.has("count")) {
                null
            } else edgeMediaLike.getString("count")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return null
    }

    private fun getComments(shortCode_media_object: JSONObject): String? {
        try {
            if (!shortCode_media_object.has("edge_media_preview_comment")) {
                return null
            }
            val edgeMediaLike = shortCode_media_object.getJSONObject("edge_media_preview_comment")
            return if (!edgeMediaLike.has("count")) {
                null
            } else edgeMediaLike.getString("count")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return null
    }

    private fun getCaption(shortCode_media_object: JSONObject): String? {
        var edgeCaption: JSONObject? = null
        try {
            if (!shortCode_media_object.has("edge_media_to_caption")) {
                return null
            }
            edgeCaption = shortCode_media_object.getJSONObject("edge_media_to_caption")
            if (!edgeCaption.has("edges")) {
                return null
            }
            val captionNode = edgeCaption.getJSONArray("edges")
            if (captionNode.length() != 0) {
                return captionNode.getJSONObject(0).getJSONObject("node").getString("text")
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return null
    }


}