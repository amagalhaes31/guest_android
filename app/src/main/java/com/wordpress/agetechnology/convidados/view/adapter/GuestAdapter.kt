package com.wordpress.agetechnology.convidados.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wordpress.agetechnology.convidados.R
import com.wordpress.agetechnology.convidados.service.model.GuestModel
import com.wordpress.agetechnology.convidados.view.listener.GuestListener
import com.wordpress.agetechnology.convidados.view.viewholder.GuestViewHolder

// Classe responsavel por criar o layout
class GuestAdapter : RecyclerView.Adapter<GuestViewHolder>() {

    // Armazena a lista de convidados
    private var mGuestList : List<GuestModel> = arrayListOf()
    private lateinit var mListener: GuestListener

    // Responsável por criar o layout da lista de convidados
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_guest, parent, false)
        return GuestViewHolder(item, mListener)
    }

    //
    override fun getItemCount(): Int {
        return mGuestList.count()
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(mGuestList[position])
    }


    fun updateGuests(list: List<GuestModel>){
        mGuestList = list
        notifyDataSetChanged()
    }

    fun attachListener(listener: GuestListener){
        mListener = listener
    }

}