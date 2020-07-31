package tra.wor.bookreaderwithkotlin.data

import android.app.Application
import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tra.wor.bookreaderwithkotlin.pojo.*

class Repositry : ViewModel() {
    var mutableLiveData = MutableLiveData<List<items>>()

    fun getdata_book(book: String): MutableLiveData<List<items>> {

        val call: Call<programming> = postclient.getClient.getposts(book)
        call.enqueue(object : Callback<programming> {

            override fun onResponse(call: Call<programming>, response: Response<programming>) {
                val programming: programming = response.body()!!
                val items: List<items?> = programming.items
                var dataList: List<items> = items as List<items>
                mutableLiveData.value = dataList
            }

            override fun onFailure(call: Call<programming>, t: Throwable) {
            }

        })

        return mutableLiveData
    }

}