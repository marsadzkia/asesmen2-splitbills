package org.d3if2107.splitbill.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "split bills")
data class SplitBillsEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var jmlhOrang: Int,
    var tagihan: Float
)
