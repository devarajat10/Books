package com.example.booklibrary.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Devashish Taragi on 10,January,2021
 */
@Entity(tableName = "new_product")
data class NewProduct(
    @SerializedName("product_desc")
    val productDesc: String,
    @SerializedName("product_quantity")
    val productQuantity: String,
    @SerializedName("product_name")
    val productName: String,
    @SerializedName("product_price")
    val productPrice: String,
    @SerializedName("user_mobile_no")
    val userMobileNumber: String = "7017392159"
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}