package com.hn.insave.utils

import android.content.Context
import com.esafirm.rxdownloader.RxDownloader
import com.hn.insave.model.PostModel
import com.hn.insave.model.SelectedContent
import com.hn.insave.model.UserMediaModel
import java.io.File

object DownloadUtil {
    @JvmStatic
    fun downloadStories(context: Context?, contents: List<UserMediaModel>, username: String) {
        var path = Config.FOLDER_NAME + "/" + username + "_" + System.currentTimeMillis() + ".jpg"
        var type = "image/*"
        for (content in contents) {
            if (content.isVideo) {
                path = Config.FOLDER_NAME + "/" + username + "_" + System.currentTimeMillis() + ".mp4"
                type = "video/*"
            }
            RxDownloader.getInstance(context).download(content.mediaUrl, path, type)
        }
    }

    fun download(context: Context?, contents: List<SelectedContent>, post: PostModel) {
        val username: String = post.username!!
        var path: String
        var type = "image/*"
        for (content in contents) {
            if (content.isVideo) {
                path = Config.FOLDER_NAME + "/" + username + "_" + System.currentTimeMillis() + ".mp4"
                type = "video/*"
            } else {
                path = Config.FOLDER_NAME + "/" + username + "_" + System.currentTimeMillis() + ".jpg"
                type = "image/*"
            }
            RxDownloader.getInstance(context).download(content.mediaUrl, path, type)
        }
    }

    @JvmStatic
    fun downloadUserInfo(context: Context, username: String, url: String) {
        var isFileAvailable = false
        val path = Constants.FOLDER_NAME_USERS + "/" + username + ".jpg"
        val type = "image/*"
        val downloadPath: String = Constants.DIRECTORY_PATH_USER
        val directory = File(downloadPath)
        val files = directory.listFiles()
        if (files != null) {
            for (file in files) {
                val filename = file.name.substring(0, file.name.lastIndexOf("."))
                if (filename == username) {
                    isFileAvailable = true
                }
            }
        }
        if (!isFileAvailable)
            RxDownloader.getInstance(context).download(url, path, type)
    }

    fun downloadContent(context: Context?, contents: List<SelectedContent>, post: PostModel) {
        val username: String = post.username!!.replace(".", "_")
        var path: String
        var type = "image/*"
        for (content in contents) {
            if (content.isVideo) {
                val split1: List<String> = content.mediaUrl.split(".mp4")
                val prefix = split1[0].substring(split1[0].lastIndexOf("/"))
                val prefix1 = prefix.split("/").toTypedArray()
                var name = prefix1[1]
                name = username + "_" + name
                if (isAvailable(name)) {
                    continue
                }
                path = Config.FOLDER_NAME + "/" + name + ".mp4"
                type = "video/*"
            } else {
                val split2: List<String> = content.mediaUrl.split(".jpg")
                val prefix = split2[0].substring(split2[0].lastIndexOf("/"))
                val prefix1 = prefix.split("/").toTypedArray()
                var name = prefix1[1]
                name = username + "_" + name
                if (isAvailable(name)) {
                    continue
                }
                path = Config.FOLDER_NAME + "/" + name + ".jpg"
                type = "image/*"
            }
            RxDownloader.getInstance(context).download(content.mediaUrl, path, type)
        }
    }

    fun downloadDP(context: Context, username: String, url: String) {
        var type = "image/*"
        val path: String = Config.FOLDER_NAME + "/" + username + "_" + System.currentTimeMillis() + ".jpg"
        type = "image/*"
        RxDownloader.getInstance(context).download(url, path, type)
    }

    private fun isDPAvailable(username: String): Boolean {
        var isUsernameAvailable = false
        val path: String = Constants.DIRECTORY_PATH
        val directory = File(path)
        val files = directory.listFiles()
        if (files != null) {
            for (file in files) {
                val filePath = file.name
                if (filePath == username) {
                    isUsernameAvailable = true
                }
            }
        }
        return isUsernameAvailable
    }


    private fun isAvailable(name: String?): Boolean {
        var aBoolean = false
        val path: String = Constants.DIRECTORY_PATH
        val directory = File(path)
        val files = directory.listFiles()
        if (files != null) {
            for (file in files) {
                val filePath = file.absolutePath
                if (filePath.contains(name!!)) {
                    aBoolean = true
                }
            }
        }
        return aBoolean
    }
}