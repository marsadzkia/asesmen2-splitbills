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
import org.d3if2107.splitbill.model.splitBill

class MainViewModel(private val db: SplitBillsDao) : ViewModel() {

    private val jumlahTagihan = MutableLiveData<JumlahTagihan?>()

    fun hitungPatungan(jmlhOrang: Int, tagihan: Int) {
        val data = SplitBillsEntity(
            jmlhOrang = jmlhOrang,
            tagihan = tagihan.toFloat()
        )
        jumlahTagihan.value = data.splitBill()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(data)
            }
        }
    }
    fun getJumlahTagihan(): LiveData<JumlahTagihan?> = jumlahTagihan
}