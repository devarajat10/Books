package com.example.booklibrary.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.booklibrary.model.NewProduct

/**
 * Created by Devashish Taragi on 10,January,2021
 */

@Database(
    entities = [NewProduct::class],
    version = 1
)
abstract class NewProductDatabase : RoomDatabase() {
    abstract fun newProductDao(): NewProductDao


    companion object {

        private var instance: NewProductDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NewProductDatabase::class.java, "new_product.db"
            ).build()
    }
}
