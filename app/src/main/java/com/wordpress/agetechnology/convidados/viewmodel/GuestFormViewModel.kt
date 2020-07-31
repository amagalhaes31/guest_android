package com.wordpress.agetechnology.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wordpress.agetechnology.convidados.service.model.GuestModel
import com.wordpress.agetechnology.convidados.service.repository.GuestRepository

/** ViewModel does not have Android Context, then use the application 'AndroidViewModel' **/
class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private var mContext = application.applicationContext
    private var mGuestRepository: GuestRepository = GuestRepository.getInstance(mContext)

    private var mSaveGuest = MutableLiveData<Boolean>()     // MutableLiveData: We can change the value
    val saveGuest: LiveData<Boolean> = mSaveGuest           // LiveData: We can not change the value

    private var mGuest = MutableLiveData<GuestModel>()     // MutableLiveData: We can change the value
    val guest: LiveData<GuestModel> = mGuest


    fun save(name: String, presence: Boolean) {
        val guest =
            GuestModel(
                name = name,
                presence = presence
            )
       mSaveGuest.value = mGuestRepository.save(guest)
    }

    fun load(id: Int){
        mGuest.value = mGuestRepository.get(id)
    }
}
