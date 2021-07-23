package com.example.booklibrary.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.booklibrary.R
import com.example.booklibrary.adapter.BooksAdapter
import com.example.booklibrary.viewModel.BookViewModel
import kotlinx.android.synthetic.main.activity_books.*

class BooksActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    var booksAdapter: BooksAdapter? = null
    var viewModel: BookViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)
        initView()
    }

    private fun initView() {
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Books"

        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)


        recyclerView = findViewById(R.id.recycler_view)
        viewModel = ViewModelProvider(this@BooksActivity).get(BookViewModel::class.java)

        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()



        viewModel?.getBookLiveData()?.observe(this, Observer {

            //working with live data to build recycler view adapter
            // if data is not null show it in recycler view else show a message to user "No books available !!"

            if (it != null && it.results.size > 0) {
                textView.visibility = View.VISIBLE
                nothing.visibility = View.INVISIBLE
                recyclerView.adapter = BooksAdapter(this@BooksActivity, it)
                booksAdapter?.notifyDataSetChanged()
            } else {
                textView.visibility = View.INVISIBLE
                nothing.visibility = View.VISIBLE

            }
        })

        viewModel?.getAvailableBooks()
    }

    // back button handling via arrow in toolbar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
