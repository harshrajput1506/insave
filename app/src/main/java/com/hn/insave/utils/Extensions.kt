package com.hn.insave.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.hn.insave.materialdialog.MaterialAlertDialog
import com.hn.insave.model.InstaUserModel


fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun View.visible(boolean: Boolean) {
    if (boolean)
        this.visibility = View.VISIBLE
    else
        this.visibility = View.GONE
}

fun View.show(boolean: Boolean) {
    if (boolean)
        this.visibility = View.INVISIBLE
    else
        this.visibility = View.GONE
}

fun isAdAvailable(position: Int): Boolean {
    if (position % PrefUtil.getInt(Constants.NUMBER_OF_ITEMS_PER_AD, Config.DEFAULT_NUMBER_OF_ITEMS_PER_AD) == 0 && position != 0) {
        return true
    }

    return false
}

fun Activity.removeAccount(user: InstaUserModel) {
    val materialAlertDialog = MaterialAlertDialog(this)
    materialAlertDialog.setTitle("Delete")
    materialAlertDialog.setMessage("Are you sure to remove this account?")
    materialAlertDialog.setCancelable(false)
    materialAlertDialog.setPositiveClick("Confirm") {
        AccountsUtil.removeAccount(user.username!!)
        it.dismiss()
    }
    materialAlertDialog.setNegativeClick("Cancel") {
        it.dismiss()
    }
    materialAlertDialog.show()
}

fun getMinutes(milliseconds1: Long, milliseconds2: Long): Int {
    val diff = milliseconds2 - milliseconds1
    val diffSeconds = diff / 1000
    val diffMinutes = diff / (60 * 1000)
    val diffHours = diff / (60 * 60 * 1000)
    val diffDays = diff / (24 * 60 * 60 * 1000)
    return diffMinutes.toInt()
}

fun formatFlag(unicode: String): String? {
    var unicode = unicode
    var flag: String? = null
    unicode = unicode.replace("U+", "0x")
    val codeArr = unicode.split(" ").toTypedArray()
    val hex1 = codeArr[0].substring(2).toInt(16)
    val hex2 = codeArr[1].substring(2).toInt(16)
    flag = String(Character.toChars(hex1)) + String(Character.toChars(hex2))
    return flag
}

fun Context.showSoftKeyboard() {
    val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun Context.hideSoftKeyboard(view: View) {
    val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

