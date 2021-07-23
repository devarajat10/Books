package com.example.booklibrary.network

import com.example.booklibrary.model.NewProduct
import com.example.booklibrary.model.GetBookResult
import com.example.booklibrary.model.PostProductResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by Devashish Taragi on 09,January,2021
 */

//http://15.206.209.151/api/getAllAvailableBooks

const val BASE_URL = "http://15.206.209.151/api/"

interface ApiInterface {

    @GET(value = "getAllAvailableBooks")
    suspend fun getAllAvailableBooks(): GetBookResult

    @POST(value = "addNewProduct")
    suspend fun postProductInfo(@Body newProduct: NewProduct) : PostProductResult

    companion object {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiInterface::class.java)
    }


}

