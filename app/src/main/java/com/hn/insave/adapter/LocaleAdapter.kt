package com.hn.insave.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.hn.insave.R
import com.hn.insave.model.LocaleModel
import com.hn.insave.utils.LocaleHelper
import com.hn.insave.utils.visible

class LocaleAdapter(val context: Context, val languages: MutableList<LocaleModel>, var callback: (locale: LocaleModel) -> Unit) : RecyclerView.Adapter<LocaleAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewLang: TextView = view.findViewById(R.id.textViewLang)
        val imgViewSelected: ImageView = view.findViewById(R.id.imgViewSelected)
        val itemLang: ConstraintLayout = view.findViewById(R.id.itemLang)

        init {
            itemLang.setOnClickListener { callback.invoke(languages[adapterPosition]) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_lang, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return languages.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lang = languages[position]
        holder.textViewLang.text = lang.name
        holder.imgViewSelected.visible(LocaleHelper.getPersistedData(context, "en") == lang.langCode)
    }

}