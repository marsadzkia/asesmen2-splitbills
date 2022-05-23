package org.d3if2107.splitbill.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SplitBillsDao {
    @Insert
    fun insert(splitbills: SplitBillsEntity)

    @Query("SELECT * FROM `split bills` ORDER BY id DESC LIMIT 1")
    fun getLastData(): LiveData<SplitBillsEntity?>
}