package org.d3if2107.splitbill.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SplitBillsEntity::class], version = 1, exportSchema = false)
abstract class SplitBillsDb : RoomDatabase() {
    abstract val dao: SplitBillsDao

    companion object{
        @Volatile
        private var INSTANCE: SplitBillsDb? = null
        fun getInstance(context: Context): SplitBillsDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SplitBillsDb::class.java,
                        "SplitBills.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}