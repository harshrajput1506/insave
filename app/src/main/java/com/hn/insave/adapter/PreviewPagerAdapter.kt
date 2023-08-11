package com.hn.insave.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.hn.insave.R
import com.hn.insave.listener.PlayVideoListener
import com.hn.insave.model.HistoryFilesModel
import java.util.*

class PreviewPagerAdapter(private val context: Context, private val historyFiles: List<HistoryFilesModel>, private val listener: PlayVideoListener) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.layout_preview, container, false)
        val content = historyFiles[position]
        val previewImg = Objects.requireNonNull(view).findViewById<ImageView>(R.id.previewImg)
        val playBtn = view!!.findViewById<ImageView>(R.id.iv_play)
        playBtn.visibility = if (content.isVideo) View.VISIBLE else View.GONE
        playBtn.setOnClickListener { v: View? -> listener.onPLay(content) }
        Glide.with(context).load(content.filePath)
                .placeholder(ContextCompat.getDrawable(context, R.drawable.img_placeholder)).into(previewImg)
        container.addView(view)
        return view
    }

    override fun getCount(): Int {
        return historyFiles.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

}