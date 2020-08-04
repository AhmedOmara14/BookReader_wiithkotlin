package tra.wor.bookreaderwithkotlin.data

import android.util.Log
import android.view.View
import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    suspend fun getdata_book(book: String): MutableLiveData<List<items>> {

        //Rxjava
        /*
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            postclient.getClient.getposts(book)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({response -> onResponse(response)}, {t -> onFailure(t) }))
        */

        //Coroutine Kotlin
        viewModelScope.launch(Dispatchers.Main) {
            val responce=postclient.getClient.getposts(book)
            val programming: programming? =responce.body()
            val items: List<items?> = programming!!.getitem()
            mutableLiveData.value=items as List<items>

        }

        return withContext(Dispatchers.IO){
            mutableLiveData
        }
    }

    companion object {
        private const val TAG = "Repositry"
    }

}



