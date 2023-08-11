package com.hn.insave.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hn.insave.R
import com.hn.insave.listener.HistoryClickListener
import com.hn.insave.model.UserModel
import com.jackandphantom.circularimageview.RoundedImage

class UserAdapter(private val context: Context, private val users: List<UserModel>, private val historyClickListener: HistoryClickListener) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_users, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        Glide.with(context).load(user.image).into(holder.profileImg)
        holder.textViewUsername.text = user.userName
        holder.profileImg.setOnClickListener { v: View? -> historyClickListener.onUserSelect(position) }

    }

    override fun getItemCount(): Int {
        return users.size
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var profileImg: RoundedImage = itemView.findViewById(R.id.profileImg)
        var textViewUsername: TextView = itemView.findViewById(R.id.textViewUsername)

        init {
            profileImg.setOnClickListener { v: View? -> historyClickListener.onUserSelect(adapterPosition) }
        }
    }

}