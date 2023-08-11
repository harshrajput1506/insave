package com.hn.insave.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hn.insave.R
import com.hn.insave.adapter.AccountsAdapter.ChangeAccountHolder
import com.hn.insave.listener.ChangeAccountListener
import com.hn.insave.model.InstaUserModel
import com.hn.insave.utils.AccountsUtil
import com.hn.insave.utils.visible
import com.jackandphantom.circularimageview.RoundedImage
import com.ornach.nobobutton.NoboButton

class AccountsAdapter(private val context: Context, private val userModelList: List<InstaUserModel>, private val listener: ChangeAccountListener) : RecyclerView.Adapter<ChangeAccountHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChangeAccountHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_account, parent, false)
        return ChangeAccountHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ChangeAccountHolder, position: Int) {
        val user = userModelList[position]
        Glide.with(context).load(user.image_url).into(holder.profileImg)
        holder.textViewName.text = user.name
        holder.textViewUsername.text = user.username
        holder.itemAccount.setOnClickListener { v: View? -> listener.onChangeAccount(user) }
        holder.textViewActive.visible(AccountsUtil.activeAccount?.username!! == user.username)
        holder.btnRemoveAccount.visible(AccountsUtil.activeAccount?.username!! != user.username)
    }

    override fun getItemCount(): Int {
        return userModelList.size
    }

    inner class ChangeAccountHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewUsername: TextView = itemView.findViewById(R.id.textViewUsername)
        var textViewName: TextView = itemView.findViewById(R.id.textViewName)
        var textViewActive: TextView = itemView.findViewById(R.id.textViewActive)
        var btnRemoveAccount: NoboButton = itemView.findViewById(R.id.btnRemoveAccount)
        var profileImg: RoundedImage = itemView.findViewById(R.id.profileImg)
        var itemAccount: ConstraintLayout = itemView.findViewById(R.id.item_account)

        init {
            btnRemoveAccount.setOnClickListener {
                listener.onAccountRemoved(userModelList[adapterPosition])
            }
        }
    }

}