package com.hn.insave.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hn.insave.R
import com.hn.insave.listener.HistoryClickListener
import com.hn.insave.listener.RVClickListener
import com.hn.insave.model.HistoryFilesModel
import java.util.*

class HistoryFileAdapter : RecyclerView.Adapter<HistoryFileAdapter.ViewHolder> {
    private var context: Context
    private var files: MutableList<HistoryFilesModel> = ArrayList()
    private var historyClickListener: HistoryClickListener? = null
    private var rvClickListener: RVClickListener? = null

    constructor(context: Context, files: MutableList<HistoryFilesModel>, historyClickListener: HistoryClickListener?) {
        this.context = context
        this.files = files
        this.historyClickListener = historyClickListener
    }

    constructor(context: Context, files: MutableList<HistoryFilesModel>, rvClickListener: RVClickListener?) {
        this.context = context
        this.files = files
        this.rvClickListener = rvClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_history_file, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val file = files[position]
        if (!file.isVideo) holder.imgViewPlay.visibility = View.GONE
        Glide.with(context)
                .load(file.filePath)
                .placeholder(ContextCompat.getDrawable(context, R.drawable.img_placeholder))
                .into(holder.imgViewFile)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return files.size
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgViewFile: ImageView = itemView.findViewById(R.id.imgViewFile)
        var imgViewPlay: ImageView = itemView.findViewById(R.id.imgViewPlay)

        init {
            imgViewFile.setOnClickListener { v: View? ->
                if (historyClickListener != null) historyClickListener!!.onMediaSelect(adapterPosition)
                if (rvClickListener != null) rvClickListener!!.onClick(adapterPosition)
            }
        }
    }
}