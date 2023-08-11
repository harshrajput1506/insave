package com.hn.insave.listener

import androidx.recyclerview.widget.RecyclerView

interface DownloadBindListener {
    fun onBind(holder: RecyclerView.ViewHolder, position: Int)
}