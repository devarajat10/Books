package com.example.booklibrary.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.booklibrary.R
import com.example.booklibrary.model.NewProduct
import com.example.booklibrary.viewModel.BookViewModel
import kotlinx.android.synthetic.main.activity_add_product.*

class AddProductActivity : AppCompatActivity() {
    lateinit var productName: TextView
    lateinit var productDesc: TextView
    lateinit var productQuantity: TextView
    lateinit var productPrice: TextView
    var viewModel: BookViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)
        init()
    }

    private fun init() {

        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Add product"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        productName = findViewById(R.id.product_name)
        productDesc = findViewById(R.id.product_desc)
        productQuantity = findViewById(R.id.quantity)
        productPrice = findViewById(R.id.price)
        viewModel = ViewModelProvider(this@AddProductActivity).get(BookViewModel::class.java)

        save.setOnClickListener {

            if (validateUI()) {

                // saving data in local storage

                viewModel?.saveDetailsInDB(NewProduct(productName.text.trim().toString(),
                        productDesc.text.trim().toString(),
                        productQuantity.text.trim().toString(),
                        productPrice.text.trim().toString()))
                Toast.makeText(
                    applicationContext,
                    "Saved locally",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            } else {
                Toast.makeText(
                    this@AddProductActivity,
                    "Please enter details in all the fields",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    // function to check if all fields are filled by the user before clicking SAVE button
    private fun validateUI(): Boolean {

        if (productName.text.trim().length > 0 &&
            productDesc.text.trim().length > 0 &&
            productQuantity.text.trim().length > 0 &&
            productPrice.text.trim().length > 0
        ) return true
        else return false
    }

    // back button handling via arrow in toolbar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}