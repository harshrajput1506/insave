package com.hn.insave.utils

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri

object InfoUtil {
    fun rateApp(activity: Activity) {
        val uri = Uri.parse("market://details?id=" + activity.application.packageName)
        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        try {
            activity.startActivity(goToMarket)
        } catch (e: ActivityNotFoundException) {
            activity.startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + activity.application.packageName)))
        }
    }

    fun shareApp(activity: Activity, text: String) {
        try {
            val i = Intent(Intent.ACTION_SEND)
            i.type = "text/plain"
            i.putExtra(Intent.EXTRA_SUBJECT, "My application name")
            var sAux = """

                $text


                """.trimIndent()
            sAux = sAux + "https://play.google.com/store/apps/details?id=" + activity.application.packageName
            i.putExtra(Intent.EXTRA_TEXT, sAux)
            activity.startActivity(Intent.createChooser(i, "choose one"))
        } catch (ignored: Exception) {
        }
    }

    fun openPrivacyPolicy(activity: Activity, url: String?) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        activity.startActivity(browserIntent)
    }

    fun moreApps(activity: Activity, url: String?) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        activity.startActivity(browserIntent)
    }
}