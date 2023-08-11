package com.hn.insave.utils

import io.paperdb.Paper
import java.util.*

class PremiumUtil {

    companion object {
        private const val CURRENT_BALANCE = "currentBalance"

        fun addCredit(credits: Int) {
            var currentCredits = Paper.book().read(CURRENT_BALANCE, 0)
            currentCredits += credits
            Paper.book().write(CURRENT_BALANCE, credits)
        }

        fun resetCredits() {
            Paper.book().write(CURRENT_BALANCE, 0)
        }

        fun credits(): Int {
            return Paper.book().read(CURRENT_BALANCE, 0)
        }

        val isPremium: Boolean
            get() = Paper.book().read(CURRENT_BALANCE, 0) >= 1000

        val isVideoAvailable: Boolean
            get() {
                val lastWatch = Date(Paper.book().read(Constants.LAST_VIDEO_TIMESTAMP, 0L))
                val current = Date(System.currentTimeMillis())
                return hoursDifference(current, lastWatch) > 2
            }

        fun setLastWatch(timestamp: Long) {
            Paper.book().write(Constants.LAST_VIDEO_TIMESTAMP, timestamp)
        }

        private fun hoursDifference(date1: Date, date2: Date): Int {
            val milliToHour = 1000 * 60 * 60
            return (date1.time - date2.time).toInt() / milliToHour
        }
    }
}