package com.example.skincareroutineplanner.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.skincareroutineplanner.presentation.screens.analytics.data.UsageEvent
import kotlinx.coroutines.internal.synchronized
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.InternalCoroutinesApi

@Database(
    entities = [Product::class, UsageEvent::class],
    version = 2,
    exportSchema = true
)
@TypeConverters(Converter::class)
abstract class ProductsDatabase : RoomDatabase() {

    abstract fun myDao(): MyDao

    companion object {
        @Volatile
        private var INSTANCE: ProductsDatabase? = null

        /**
         * Если вам не важно сохранять старые данные при обновлении схемы,
         * можно раскомментировать `.fallbackToDestructiveMigration()`.
         * Иначе (для продакшена) лучше прописать MIGRATION_1_2.
         */
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                // создаём таблицу для событий использования
                db.execSQL("""
                  CREATE TABLE IF NOT EXISTS usage_events (
                    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                    productId INTEGER NOT NULL,
                    timestamp INTEGER NOT NULL
                  )
                """.trimIndent())
            }
        }

        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): ProductsDatabase {
            return INSTANCE ?: synchronized(this) {
                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    ProductsDatabase::class.java,
                    "usersProductDatabase"
                )
                    // вариант A: безопасная миграция (удалит старые данные)
                    // .fallbackToDestructiveMigration()

                    // вариант B: ручная миграция (сохранит старые данные)
                    .addMigrations(MIGRATION_1_2)

                    .allowMainThreadQueries()

                val instance = builder.build()
                INSTANCE = instance
                instance
            }
        }
    }
}