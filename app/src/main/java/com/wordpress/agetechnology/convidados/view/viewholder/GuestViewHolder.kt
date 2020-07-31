package com.wordpress.agetechnology.convidados.view.viewholder

import android.content.ContentResolver
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wordpress.agetechnology.convidados.R
import com.wordpress.agetechnology.convidados.service.model.GuestModel
import com.wordpress.agetechnology.convidados.view.listener.GuestListener


// Guarda as referencias dos elementos de layout (interface)
class GuestViewHolder(itemView: View, private val listener: GuestListener) : RecyclerView.ViewHolder(itemView) {

    fun bind(guest: GuestModel){
        val textName = itemView.findViewById<TextView>(R.id.text_name)
        textName.text = guest.name

        textName.setOnClickListener{
            listener.onClick(guest.id)
        }
    }
}