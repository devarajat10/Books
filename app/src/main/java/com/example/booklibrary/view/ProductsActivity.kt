package com.example.booklibrary.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.booklibrary.R
import com.example.booklibrary.viewModel.BookViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_products.*

class ProductsActivity : AppCompatActivity() {
    var viewModel: BookViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        initView()

        //setting listner

        add_products.setOnClickListener {
            startActivity(Intent(this@ProductsActivity, AddProductActivity::class.java))

        }

        sync_products.setOnClickListener {
            viewModel?.syncAllProductsFromDb()
            progress.visibility = View.VISIBLE

        }

        viewModel?.getSyncingStatus()?.observe(this, Observer {
            progress.visibility = View.GONE

            //showing if data is synced successfully or not

            Toast.makeText(this@ProductsActivity, it, Toast.LENGTH_SHORT).show()


        })

    }

    private fun initView() {
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Products"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this@ProductsActivity).get(BookViewModel::class.java)

    }

    // back button handling via arrow in toolbar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
