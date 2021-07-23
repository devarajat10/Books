package com.example.booklibrary.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.booklibrary.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setting listner on book button
        // Using direct reference of XML to save variable space
        books.setOnClickListener {
            startActivity(Intent(this@MainActivity, BooksActivity::class.java))

        }

        // Setting listner on add products button
        // Using direct reference of XML to save variable space
        products.setOnClickListener {
            startActivity(Intent(this@MainActivity, ProductsActivity::class.java))
        }
    }
}
