package tra.wor.bookreaderwithkotlin.data

import android.util.Log
import android.view.View
import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tra.wor.bookreaderwithkotlin.pojo.items
import tra.wor.bookreaderwithkotlin.pojo.programming

class Repositry : ViewModel() {
    var mutableLiveData = MutableLiveData<List<items>>()
    fun onFailure(t: Throwable) {
    }

    fun onResponse(response: programming) {
        val items: List<items?> = response.items
        mutableLiveData.setValue(items as List<items>?)
    }

    fun getdata_book(book: String): MutableLiveData<List<items>> {
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            postclient.getClient.getposts(book)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({response -> onResponse(response)}, {t -> onFailure(t) }))

        return mutableLiveData
    }

    companion object {
        private const val TAG = "Repositry"
    }

}



