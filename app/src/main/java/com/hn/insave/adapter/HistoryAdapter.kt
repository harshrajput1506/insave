package com.hn.insave.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.hn.insave.R
import com.hn.insave.listener.HistoryClickListener
import com.hn.insave.model.HistoryFilesModel
import com.hn.insave.model.UserModel

class HistoryAdapter(private val context: Context, private val users: List<UserModel>, private val historyFiles: MutableList<HistoryFilesModel>, private val historyClickListener: HistoryClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val viewUser = 0
    private var userAdapter: UserAdapter? = null
    private var historyFileAdapter: HistoryFileAdapter? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        return if (viewType == viewUser) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_users, parent, false)
            UserViewHolder(view)
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.layout_history_files, parent, false)
            HistoryViewModel(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            viewUser
        } else 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is UserViewHolder) {
            userAdapter = UserAdapter(context, users, historyClickListener)
            holder.listUsers.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            holder.listUsers.adapter = userAdapter
        }
        if (holder is HistoryViewModel) {
            historyFileAdapter = HistoryFileAdapter(context, historyFiles, historyClickListener)
            holder.listHistory!!.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            holder.listHistory!!.adapter = historyFileAdapter
            holder.listHistory!!.addItemDecoration(SpacesItemDecoration(4))
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    internal class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var listUsers: RecyclerView = itemView.findViewById(R.id.listUsers)
    }

    internal class HistoryViewModel(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var listHistory: RecyclerView? = itemView.findViewById(R.id.listHistory)
    }

}