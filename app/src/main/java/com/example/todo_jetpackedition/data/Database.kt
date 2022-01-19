package com.example.todo_jetpackedition

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo_jetpackedition.data.Dao

@Database(entities = [Data::class], version = 3, exportSchema = false)
abstract class dDatabase: RoomDatabase() {
    abstract fun dataDao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: dDatabase? = null

        fun getDatabase(context: Context): dDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    dDatabase::class.java,
                    "data_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
