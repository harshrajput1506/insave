package com.hn.insave.utils

import com.hn.insave.model.InstaUserModel
import com.hn.insave.model.UserModel
import io.paperdb.Paper

class AccountsUtil private constructor() {
    companion object {
        private const val CURRENT_ACCOUNT = "currentAccount"
        private const val ACCOUNTS_BOOK = "accountsBook"
        private const val USER_BOOK = "downloadBook"

        @JvmStatic
        val activeAccount: InstaUserModel
            get() {
                return Paper.book().read(CURRENT_ACCOUNT)
            }

        @JvmStatic
        fun setActiveAccount(user: InstaUserModel) {
            Paper.book().write(CURRENT_ACCOUNT, user)
        }

        @JvmStatic
        fun addAccount(user: InstaUserModel) {
            Paper.book(ACCOUNTS_BOOK).write(user.username, user)
            setActiveAccount(user)
        }

        @JvmStatic
        val isLoggedIn: Boolean
            get() = accounts.isNotEmpty()

        val accounts: List<InstaUserModel>
            get() {
                val accounts: MutableList<InstaUserModel> = ArrayList()
                val accountsKeys = Paper.book(ACCOUNTS_BOOK).allKeys
                for (key in accountsKeys) {
                    val account = Paper.book(ACCOUNTS_BOOK).read<InstaUserModel>(key)
                    if (account.username == activeAccount?.username) {
                        accounts.add(0, account)
                    } else {
                        accounts.add(account)
                    }
                }
                return accounts
            }

        @JvmStatic
        fun addUser(user: UserModel) {
            if (!Paper.book(USER_BOOK).contains(user.userName)) {
                Paper.book(USER_BOOK).write(user.userName, user)
            }
        }

        @JvmStatic
        val users: MutableList<UserModel>
            get() {
                val users: MutableList<UserModel> = ArrayList()
                val keys = Paper.book(USER_BOOK).allKeys
                for (key in keys) {
                    val user = Paper.book(USER_BOOK).read<UserModel>(key)
                    users.add(user)
                }
                return users
            }

        @JvmStatic
        fun removeAccount(username: String) {
            Paper.book(ACCOUNTS_BOOK).delete(username)
        }
    }
}