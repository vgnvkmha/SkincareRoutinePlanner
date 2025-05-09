package com.example.skincareroutineplanner.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


@Database(entities = [Product::class], version = 1, exportSchema = true)
abstract class ProductsDatabase : RoomDatabase() {
    abstract fun myDao(): MyDao

    @OptIn(InternalCoroutinesApi::class)
    companion object {
        @Volatile
        private var INSTANCE: ProductsDatabase? = null

        fun getDatabase(context: Context) : ProductsDatabase {
            val tempInstance = INSTANCE
            tempInstance?.let {
                return tempInstance
            }
            synchronized(this ) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductsDatabase::class.java,
                    "usersProductDatabase"
                )   .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}