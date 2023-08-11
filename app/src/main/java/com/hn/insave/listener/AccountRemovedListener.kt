package com.hn.insave.listener

import com.hn.insave.model.InstaUserModel

interface AccountRemovedListener {
    fun onAccountRemoved(user: InstaUserModel)
}