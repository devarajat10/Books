package com.example.booklibrary.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.booklibrary.R
import com.example.booklibrary.model.GetBookResult
import java.lang.Exception

class BooksAdapter(var context: Context, private var booksList: GetBookResult?) :
    RecyclerView.Adapter<BooksAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var name: TextView = view.findViewById(R.id.book_name)
        var desc: TextView = view.findViewById(R.id.book_desc)
        var author: TextView = view.findViewById(R.id.book_author)
        var price: TextView = view.findViewById(R.id.book_price)
        var image: ImageView = view.findViewById(R.id.book_image)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val book = booksList?.results?.get(position)
        holder.name.text = book?.bookName
        holder.desc.text = book?.bookDesc
        holder.author.text = "Author:  " + book?.bookAuthor
        holder.price.text = "Price: \u20B9" + book?.bookPrice
        try {
            //load image from URI to image view
            Glide.with(context)
                .load(book?.bookImgUrl)
                .into(holder.image)
        } catch (e: Exception) {
            Log.e("dev", e.printStackTrace().toString())
        }
    }

    override fun getItemCount(): Int {
        return booksList?.results?.size ?: 0
    }
}
