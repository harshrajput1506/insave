package com.hn.insave.listener

import com.hn.insave.model.UserModel

interface StorySelectListener {
    fun onStorySelect(userModel: UserModel?)
}