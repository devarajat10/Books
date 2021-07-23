package com.example.booklibrary.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.booklibrary.repository.Repository
import com.example.booklibrary.model.NewProduct
import com.example.booklibrary.model.GetBookResult
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

/**
 * Created by Devashish Taragi on 10,January,2021
 */
class BookViewModel(application: Application) : AndroidViewModel(application) {
    protected val compositeDisposable = CompositeDisposable()

    var repository: Repository? = null

    var books = MutableLiveData<GetBookResult>()

    var isSyncingComplete = MutableLiveData<String>()

    init {
        if (repository == null)
            repository =
                Repository(getApplication<Application>().applicationContext)
    }

    // return a live data
    fun getBookLiveData(): MutableLiveData<GetBookResult> {
        return books

    }

    //fetch data from API i.e. all the available books and notify the observers of live data
    fun getAvailableBooks() {
        CoroutineScope(IO).launch {
            val res = (repository?.getAllAvailableBooks())
            books.postValue(res)
        }
    }


    // return a livedata to notify observers about completion of syncing
    fun getSyncingStatus(): MutableLiveData<String> {
        return isSyncingComplete
    }


    // save details in database
    fun saveDetailsInDB(newProduct: NewProduct) {
        CoroutineScope(IO).launch {
            repository?.addNewProductInDb(newProduct)
        }
    }


    // get data from Local DB and post all data to API
    fun syncAllProductsFromDb() {
        val message: String = "Syncing successful"
        repository?.getAllProductsfromDb()?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())?.
            subscribe({ it ->
                if (!it.isNullOrEmpty()) {
                    isSyncingComplete.postValue(message)

                } else {
                    isSyncingComplete.postValue(message)
                }
                it?.forEach {
                    Log.v("Person Name", it.productDesc)
                }
            }, {
            })?.let {
            compositeDisposable.add(it)
        }
        }
    }

