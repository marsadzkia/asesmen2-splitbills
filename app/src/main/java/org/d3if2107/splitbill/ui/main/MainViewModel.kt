package org.d3if2107.splitbill.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2107.splitbill.db.SplitBillsDao
import org.d3if2107.splitbill.db.SplitBillsEntity
import org.d3if2107.splitbill.model.JumlahTagihan

class MainViewModel(private val db: SplitBillsDao) : ViewModel() {

    private val jumlahTagihan = MutableLiveData<JumlahTagihan?>()
    val data = db.getLastData()

    fun hitungPatungan(jmlhOrang: Int, tagihan: Int) {
        val jmlhOrgF = jmlhOrang.toFloat()
        val ppn = tagihan.toFloat() * (11 / 100)
        val patungan = ((tagihan.toFloat() + ppn) / jmlhOrgF).toString()
        jumlahTagihan.value = JumlahTagihan(patungan)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val data = SplitBillsEntity(
                    jmlhOrang = jmlhOrang,
                    tagihan = tagihan.toFloat()
                )
                db.insert(data)
            }
        }
    }

    fun getJumlahTagihan(): LiveData<JumlahTagihan?> = jumlahTagihan
}