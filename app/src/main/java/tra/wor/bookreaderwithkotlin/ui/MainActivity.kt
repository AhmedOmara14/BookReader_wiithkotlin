package tra.wor.bookreaderwithkotlin.ui

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tra.wor.bookreaderwithkotlin.R
import tra.wor.bookreaderwithkotlin.data.Repositry
import tra.wor.bookreaderwithkotlin.data.postclient
import tra.wor.bookreaderwithkotlin.pojo.items
import tra.wor.bookreaderwithkotlin.pojo.programming
import java.security.AccessController.getContext
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity(){

   // private lateinit var job: Job
   // override val coroutineContext: CoroutineContext
    //    get() = job + Dispatchers.Main

    lateinit var adapter: Adapter
    lateinit var recyclerView: RecyclerView
    lateinit var dataList: List<items>

    lateinit var repositry: Repositry
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        repositry = ViewModelProviders.of(this).get(Repositry::class.java)
        recyclerView = findViewById(R.id.rec)
        recyclerView.layoutManager = LinearLayoutManager(this)
      //  job = Job()


       GlobalScope.launch(Dispatchers.Main) {
            repositry.getdata().observe(this@MainActivity, Observer { item ->
                adapter.setlist(item)
                adapter.notifyDataSetChanged()
                recyclerView.adapter = adapter
            })
        }


        adapter = Adapter(this)

    }

}

