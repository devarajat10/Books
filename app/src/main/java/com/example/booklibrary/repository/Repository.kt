package com.example.booklibrary.repository

import android.content.Context
import android.util.Log
import com.example.booklibrary.database.NewProductDatabase
import com.example.booklibrary.network.ApiInterface
import com.example.booklibrary.model.NewProduct
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.net.ConnectException
import com.example.booklibrary.model.GetBookResult
import com.example.booklibrary.model.PostProductResult
import io.reactivex.Single
import java.lang.Exception


/**
 * Created by Devashish Taragi on 10,January,2021
 */

class Repository(var context: Context) {

    // fetch books from REST Api
    suspend fun getAllAvailableBooks(): GetBookResult? {
        var availableBooks: GetBookResult? = null
        try {
            availableBooks = ApiInterface.retrofit.getAllAvailableBooks()
            Log.d("Dev", availableBooks.toString())
        } catch (e: ConnectException) {

        }

        return availableBooks
    }

    // post product details in REST Api
    suspend fun postDataToAPI(res: NewProduct): PostProductResult? {
        try{
            var result = ApiInterface.retrofit.postProductInfo(res)
            Log.d("Dev", result.toString())

            return result
        }
        catch (e:Exception){
            return null
        }
    }

    //add product info in DB
    suspend fun addNewProductInDb(newProduct: NewProduct) {
        CoroutineScope(IO).launch {
            try {
                NewProductDatabase.invoke(context).newProductDao().addProduct(newProduct)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    //get product info from DB
    fun getAllProductsfromDb(): Single<List<NewProduct>> {
        try {
            return NewProductDatabase.invoke(context).newProductDao().getProduct()
        } catch (e: Exception) {
            return Single.error(e)
        }

    }


}