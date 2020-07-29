package com.wordpress.agetechnology.convidados.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wordpress.agetechnology.convidados.R
import com.wordpress.agetechnology.convidados.service.model.GuestModel
import kotlinx.android.synthetic.main.row_guest.view.*

// Guarda as referencias dos elementos de layout (interface)
class GuestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(guest: GuestModel){
        val textName = itemView.findViewById<TextView>(R.id.text_name)
        textName.text = guest.name
    }
}