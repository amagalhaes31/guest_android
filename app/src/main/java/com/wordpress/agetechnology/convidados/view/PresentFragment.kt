package com.wordpress.agetechnology.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wordpress.agetechnology.convidados.R
import com.wordpress.agetechnology.convidados.service.constants.GuestConstants
import com.wordpress.agetechnology.convidados.view.adapter.GuestAdapter
import com.wordpress.agetechnology.convidados.view.listener.GuestListener
import com.wordpress.agetechnology.convidados.viewmodel.GuestsViewModel

class PresentFragment : Fragment() {

    private lateinit var mViewModel: GuestsViewModel
    private val mAdapter: GuestAdapter = GuestAdapter()
    private lateinit var mListener: GuestListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mViewModel = ViewModelProvider(this).get(GuestsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_present, container, false)

        // 1) Get the recycler
        // Elemento de interface - RecyclerView
        // Não é possível deixar o Kotlin fazer o mapeamento, pois a fragment ainda não está totalmente criada
        // Assim, precisamos buscar o elemento através de findViewById
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_presents)

        // 2) Define a layout
        // Atribui um layout que diz como a RecyclerView se comporta
        recycler.layoutManager = LinearLayoutManager(context)       // LinearLayout como padrão a orientação é VERTICAL

        // 3) Define an adapter
        // Faz a ligação da RecyclerView com a listagem de itens
        recycler.adapter = mAdapter

        // Interface
        mListener = object : GuestListener{
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUESTID, id)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.load(GuestConstants.FILTER.PRESENT)
            }
        }

        mAdapter.attachListener(mListener)
        // Responsável por apresentar a lista dos convidados
        observer()

        return root
    }

    override fun onResume() {
        super.onResume()
        mViewModel.load(GuestConstants.FILTER.PRESENT)
    }
    private fun observer() {
        mViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)
        })
    }
}

