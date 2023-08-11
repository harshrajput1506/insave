package com.hn.insave.listener

import com.hn.insave.model.HistoryFilesModel

interface PlayVideoListener {
    fun onPLay(file: HistoryFilesModel?)
}