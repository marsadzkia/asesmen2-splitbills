package org.d3if2107.splitbill.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2107.splitbill.db.SplitBillsDao

class HistoryViewModel(private val db: SplitBillsDao) : ViewModel() {
    val data = db.getLastData()

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }
}