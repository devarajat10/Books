package com.example.booklibrary.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.booklibrary.model.NewProduct
import io.reactivex.Single

/**
 * Created by Devashish Taragi on 10,January,2021
 */

@Dao
interface NewProductDao {
    @Insert
    suspend fun addProduct(newProduct: NewProduct)


    @Query(value = "select * from new_product")
    fun getProduct(): Single<List<NewProduct>>
}