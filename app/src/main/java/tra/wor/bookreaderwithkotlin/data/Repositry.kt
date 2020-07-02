package tra.wor.bookreaderwithkotlin.data

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tra.wor.bookreaderwithkotlin.pojo.items
import tra.wor.bookreaderwithkotlin.pojo.programming
import java.util.ArrayList

class Repositry : ViewModel() {
     var mutableLiveData =MutableLiveData<List<items>>()

    fun getdata() : MutableLiveData<List<items>> {
        val call: Call<programming> = postclient.getClient.getposts("programming")
        call.enqueue(object : Callback<programming> {

            override fun onResponse(call: Call<programming>, response: Response<programming>) {
                val programming : programming = response.body()!!
                val items: List<items?> = programming.items
                var dataList: List<items> = items as List<items>
                 mutableLiveData.value=dataList
            }

            override fun onFailure(call: Call<programming>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return mutableLiveData
    }
    companion object {
        private const val TAG = "Repositry"
    }
}