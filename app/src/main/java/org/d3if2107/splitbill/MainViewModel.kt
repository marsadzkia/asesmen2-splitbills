package org.d3if2107.splitbill

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if2107.splitbill.model.JumlahTagihan

class MainViewModel : ViewModel() {

    private val jumlahTagihan = MutableLiveData<JumlahTagihan?>()

    fun hitungPatungan(jmlhOrang: Int, tagihan: Int) {
        val jmlhOrgF = jmlhOrang.toFloat()
        val ppn = tagihan.toFloat() * 11 / 100
        val patungan = ((tagihan.toFloat() + ppn) / jmlhOrgF).toString()
        jumlahTagihan.value = JumlahTagihan(patungan)
    }

    fun getJumlahTagihan(): LiveData<JumlahTagihan?> = jumlahTagihan
}