package com.wordpress.agetechnology.convidados.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.wordpress.agetechnology.convidados.R
import com.wordpress.agetechnology.convidados.viewmodel.AbsentViewModel

class AbsentFragment : Fragment() {

    private lateinit var absentViewModel: AbsentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        absentViewModel = ViewModelProvider(this).get(AbsentViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_absent, container, false)

        /**
         * RecyclerView -> Listing of the elements
         * 1) Get the recycler
         * 2) Define a layout
         * 3) Define an adapter
         */



        return root
    }
}