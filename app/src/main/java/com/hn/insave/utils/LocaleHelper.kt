package com.hn.insave.utils

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import java.util.*

object LocaleHelper {
    const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"

    fun onAttach(context: Context): Context {
        val lang = getPersistedData(context, Locale.getDefault().language)
        return setLocale(context, lang)
    }

    fun onAttach(context: Context, defaultLang: String): Context {
        val lang = getPersistedData(context, defaultLang)
        return setLocale(context, lang)
    }

    fun setLocale(context: Context, lang: String): Context {
        persist(context, lang)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            updateResources(context, lang)
        } else updateResourceLegacy(context, lang)
    }

    private fun updateResourceLegacy(context: Context, lang: String): Context {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val resources = context.resources
        val config = resources.configuration
        config.locale = locale
        config.setLayoutDirection(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
        return context
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context, lang: String): Context {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = context.resources.configuration
        config.setLocale(locale)
        config.setLayoutDirection(locale)
        return context.createConfigurationContext(config)
    }

    private fun persist(context: Context, lang: String?) {
        val preferences = context.getSharedPreferences("pref_locale", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(SELECTED_LANGUAGE, lang)
        editor.commit()
    }

    fun getPersistedData(context: Context, language: String): String {
        val preferences = context.getSharedPreferences("pref_locale", Context.MODE_PRIVATE)
        return preferences.getString(SELECTED_LANGUAGE, language)!!
    }


}