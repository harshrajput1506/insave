package com.hn.insave.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.hn.insave.R
import com.hn.insave.model.InstaContent
import com.hn.insave.model.SelectedContent
import java.util.*

class ContentAdapter(private val context: Context, contents: List<InstaContent>, changeListener: ContentChangeListener) : RecyclerView.Adapter<ContentAdapter.ViewHolder>() {
    private val contents: List<InstaContent> = contents
    private val selectedContent: MutableList<SelectedContent> = ArrayList<SelectedContent>()
    private val changeListener: ContentChangeListener = changeListener
    private val contentList: MutableList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.layout_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val content: InstaContent = contents[position]
        val url: String = (if (content.isVideo) content.videoThumbnailUrl else content.url).toString()
        Glide.with(context)
                .load(url)
                .placeholder(getDrawable(context, R.drawable.img_placeholder))
                .into(holder.contentImg)
        val type: Int = if (content.isVideo) R.drawable.ic_video else R.drawable.ic_photo
        holder.typeImg.setImageDrawable(getDrawable(context, type))
        val visibility = if (contentList.indexOf(content.url) == -1) View.GONE else View.VISIBLE
        holder.selectedView.visibility = visibility
        holder.selectCheck.isChecked = contentList.indexOf(content.url) != -1
    }

    override fun getItemCount(): Int {
        return contents.size
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var contentImg: ImageView = itemView.findViewById(R.id.contentImg)
        var selectedView: View = itemView.findViewById(R.id.selectedView)
        var typeImg: ImageView = itemView.findViewById(R.id.typeImg)
        var selectCheck: CheckBox = itemView.findViewById(R.id.selectCheck)

        init {
            ButterKnife.bind(this, itemView)
            itemView.setOnClickListener { v: View? -> selectContent(adapterPosition) }
            selectCheck.setOnClickListener { v: View? -> selectContent(adapterPosition) }
        }
    }

    private fun selectContent(position: Int) {
        val content: InstaContent = contents[position]
        if (contentList.indexOf(content.url) == -1) {
            selectedContent.add(SelectedContent(content.url!!, content.isVideo))
            contentList.add(content.url!!)
        } else {
            contentList.remove(content.url)
            selectedContent.removeAt(position)
        }
        changeListener.onChange(selectedContent)
        notifyItemChanged(position)
    }

    interface ContentChangeListener {
        fun onChange(selectedContent: List<SelectedContent>)
    }

    init {
        for (content in contents) {
            contentList.add(content.url!!)
            selectedContent.add(SelectedContent(content.url!!, content.isVideo))
        }
    }
}