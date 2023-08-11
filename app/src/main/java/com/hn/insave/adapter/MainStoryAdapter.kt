package com.hn.insave.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.ads.nativetemplates.TemplateView
import com.hn.insave.R
import com.hn.insave.admob.AdHelper
import com.hn.insave.listener.StorySelectListener
import com.hn.insave.model.UserModel
import com.hn.insave.utils.visible
import com.jackandphantom.circularimageview.RoundedImage

class MainStoryAdapter(private val context: Context, private val storySelectListener: StorySelectListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val adView = 1
    private val adsArray = SparseBooleanArray()
    private var users: ArrayList<UserModel> = ArrayList()

    fun updateUsers(users: ArrayList<UserModel>) {
        this.users = users
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == adView) {
            val view = LayoutInflater.from(context).inflate(R.layout.item_adview, parent, false)
            return AdViewHolder(view)
        }
        val view = LayoutInflater.from(context).inflate(R.layout.item_stories, parent, false)
        return StoryHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is StoryHolder) {
            val user = users[position]
            holder.txtViewName.text = user.realName
            holder.txtViewUsername.text = user.userName
            Glide.with(context).load(user.image).into(holder.profileImg)
            holder.itemView.setOnClickListener { storySelectListener.onStorySelect(user) }
        } else {
            if (adsArray[position]) {
                return
            }

            (holder as AdViewHolder).adTemplateView.visibility = View.GONE
            AdHelper.loadNativeAd{
                holder.adTemplateView.visible(true)
                holder.adTemplateView
            }
            adsArray.put(position, true)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (users[position].isAdd) {
            adView
        } else 0
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    internal inner class StoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtViewUsername: TextView = itemView.findViewById(R.id.txtViewUsername)
        var txtViewName: TextView = itemView.findViewById(R.id.textViewUsername)
        var profileImg: RoundedImage = itemView.findViewById(R.id.profileImg)

    }

    internal class AdViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val adTemplateView: TemplateView = itemView.findViewById(R.id.nativeTemplate)
    }

}