package com.example.recyclerviewnew

import android.graphics.ColorSpace
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.add_dialog.view.*
import kotlinx.android.synthetic.main.item.view.*

class MyViewHolder(private val view: View ): RecyclerView.ViewHolder(view) {

    fun populate (data: Model, listener: OnClickListener, pos: Int) {
        view.tvtitle.text = data.title
        view.tvDescription.text = data.description
        view.setOnClickListener {
            listener.click()
        }
        view.setOnLongClickListener {
            listener.setOnLongClick(view, pos)
            return@setOnLongClickListener true
        }
    }


}