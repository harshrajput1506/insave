package com.hn.insave.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hn.insave.R
import com.hn.insave.listener.RVClickListener
import com.hn.insave.model.UserModel
import com.jackandphantom.circularimageview.RoundedImage
import com.ornach.nobobutton.NoboButton
import java.util.*
import kotlin.collections.ArrayList

class SearchUserAdapter(private val context: Context, users: MutableList<UserModel>, clickListener: RVClickListener) : RecyclerView.Adapter<SearchUserAdapter.ViewHolder>() {
    private var users: MutableList<UserModel> = ArrayList()
    private val clickListener: RVClickListener
    private val userList: MutableList<UserModel> = ArrayList()

    init {
        this.users = users
        this.clickListener = clickListener
        userList.addAll(users)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_account, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        Glide.with(context).load(user.image).into(holder.profileImg)
        holder.textViewName.text = user.realName
        holder.textViewUsername.text = user.userName
        holder.btnRemoveAccount.visibility = View.GONE
        holder.textViewActive.visibility = View.GONE
    }

    override fun getItemCount(): Int {
        return users.size
    }

    val filter: Filter = object : Filter() {
        override fun performFiltering(charSequence: CharSequence): FilterResults {
            val searchUser: MutableList<UserModel> = ArrayList()
            if (charSequence.toString().isEmpty()) {
                searchUser.clear()
                searchUser.addAll(userList)
            } else {
                val filterPattern = charSequence.toString().toLowerCase(Locale.getDefault())
                for (user in userList) {
                    if (user.userName!!.contains(filterPattern) || user.realName!!.contains(filterPattern)) {
                        searchUser.add(user)
                    }
                }
            }
            val results = FilterResults()
            results.values = searchUser
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            users.clear()
            users.addAll((results.values as Collection<UserModel>))
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewName: TextView = itemView.findViewById(R.id.textViewName)
        var textViewUsername: TextView = itemView.findViewById(R.id.textViewUsername)
        var profileImg: RoundedImage = itemView.findViewById(R.id.profileImg)
        var itemAccount: ConstraintLayout = itemView.findViewById(R.id.item_account)
        var textViewActive: TextView = itemView.findViewById(R.id.textViewActive)
        var btnRemoveAccount: NoboButton = itemView.findViewById(R.id.btnRemoveAccount)

        init {
            itemAccount.setOnClickListener { v: View? -> clickListener.onClick(adapterPosition) }
        }
    }


}