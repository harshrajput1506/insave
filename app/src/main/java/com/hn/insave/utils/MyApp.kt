package com.hn.insave.utils

import android.app.Application
import android.content.Context
import com.downloader.PRDownloader
import com.hn.insave.admob.AdHelper
import com.onesignal.OneSignal
import io.paperdb.Paper

class MyApp : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"))
    }

    override fun onCreate() {
        super.onCreate()
        PRDownloader.initialize(this)
        Paper.init(this)
        OneSignal.startInit(this).inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true).init()

        AdHelper.initAdMob(this, Config.appId)
                .setBannerId(Config.bannerId)
                .setInterstitialId(Config.interstitialId)
                .setNativeId(Config.nativeId)
                .load()

    }

    companion object {
        private lateinit var instance: MyApp

        @JvmStatic
        fun appContext(): Context {
            return instance.applicationContext
        }

    }

    init {
        instance = this
    }
}