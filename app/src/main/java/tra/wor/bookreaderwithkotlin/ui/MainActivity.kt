package tra.wor.bookreaderwithkotlin.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tra.wor.bookreaderwithkotlin.R
import tra.wor.bookreaderwithkotlin.data.Repositry
import tra.wor.bookreaderwithkotlin.data.postclient
import tra.wor.bookreaderwithkotlin.pojo.items
import tra.wor.bookreaderwithkotlin.pojo.programming

class MainActivity : AppCompatActivity() {
    lateinit var adapter: Adapter
    lateinit var recyclerView: RecyclerView
     lateinit var dataList: List<items>

    lateinit var repositry: Repositry
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        repositry=ViewModelProviders.of(this).get(Repositry::class.java)


        recyclerView = findViewById(R.id.rec)
        recyclerView.layoutManager = LinearLayoutManager(this)

           repositry.getdata().observe(this, Observer {item->
               adapter.setlist(item)
               adapter.notifyDataSetChanged()
               recyclerView.adapter=adapter
         } )

        adapter = Adapter( this)

    }
    private fun getData() {
        val call: Call<programming> = postclient.getClient.getposts("programming")
        call.enqueue(object : Callback<programming> {

            override fun onResponse(call: Call<programming>, response: Response<programming>) {
                val programming :programming= response.body()!!
                val items: List<items?> = programming.items
                dataList= items as List<items>

            }

            override fun onFailure(call: Call<programming>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}

