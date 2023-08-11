package com.hn.insave.listener

import com.hn.insave.model.InstaUserModel

interface ChangeAccountListener {
    fun onChangeAccount(user: InstaUserModel)
    fun onAccountRemoved(user: InstaUserModel)
}