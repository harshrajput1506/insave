package com.hn.insave.utils

import android.os.Environment
import com.hn.insave.model.LocaleModel

object Constants {
    const val CREDIT_MIN = "credit_min"
    const val CREDIT_MAX = "credi_max"
    const val REGULAR_PLAN_ACCOUNT_LIMIT = "regular_plan_account_limit"
    const val NUMBER_OF_ITEMS_PER_AD = "number_of_items_per_ad"
    const val TOTAL_NO_CLICKS = "total_no_clicks"
    const val CURRENT_CLICKS = "current_clicks"
    const val LAST_AD_TIMESTAMP = "last_ad_timestamp"
    const val LAST_VIDEO_TIMESTAMP = "last_video_timestamp"
    const val PREVIEW_OBJ = "preview_obj"
    const val USERNAME = "username"
    const val PROFILE_URL = "profile_url"
    const val PREVIEW_POSITION = "preview_pos"
    const val LANGUAGE = "language"
    const val storyUrl = "https://i.instagram.com/api/v1/feed/reels_tray/"
    val DIRECTORY_PATH = Environment.getExternalStorageDirectory().absolutePath + "/Download/" + Config.FOLDER_NAME + "/"
    val DIRECTORY_PATH_USER = Environment.getExternalStorageDirectory().absolutePath + "/Download/" + Config.FOLDER_NAME + "/.users/"
    const val FOLDER_NAME_USERS = Config.FOLDER_NAME + "/.users"


//    val localeData: MutableList<LocaleModel>
//        get() {
//            val locales = ArrayList<LocaleModel>()
//            locales.add(LocaleModel("English", "en"))
//            locales.add(LocaleModel("Arabic \u200Eالعربية\u200E", "ar"))
//            locales.add(LocaleModel("German Deutsch", "de"))
//            locales.add(LocaleModel("French français", "fr"))
//            locales.add(LocaleModel("Hindi हिन्दी", "hi"))
//            locales.add(LocaleModel("Japanese 日本語", "ja"))
//            locales.add(LocaleModel("Korean 한국어", "ko"))
//            locales.add(LocaleModel("Polish polski", "pl"))
//            locales.add(LocaleModel("Russian русский", "ru"))
//            locales.add(LocaleModel("Ukrainian українська", "uk"))
//            locales.add(LocaleModel("Chinese Simplified 中文 简体", "zh-rCN"))
//            locales.add(LocaleModel("Chinese Traditional 中文 繁體", "zh-rTW"))
//            return locales
//        }

    val localeData: MutableList<LocaleModel>
        get() {
            val locales = ArrayList<LocaleModel>()
            locales.add(LocaleModel("English", "en"))
            locales.add(LocaleModel("German Deutsch", "de"))
            locales.add(LocaleModel("French français", "fr"))
            locales.add(LocaleModel("Korean 한국어", "ko"))
            locales.add(LocaleModel("Polish polski", "pl"))
            locales.add(LocaleModel("Russian русский", "ru"))
            locales.add(LocaleModel("Ukrainian українська", "uk"))
            locales.add(LocaleModel("Chinese Simplified 中文 简体", "zh-rCN"))
            locales.add(LocaleModel("Chinese Traditional 中文 繁體", "zh-rTW"))
            return locales
        }

    const val MAIN_DOWNLOAD_LOGS = "mainLogs"
}