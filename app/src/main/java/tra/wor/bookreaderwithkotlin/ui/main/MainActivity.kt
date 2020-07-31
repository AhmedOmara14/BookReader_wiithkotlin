package tra.wor.bookreaderwithkotlin.ui.main

import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import tra.wor.bookreaderwithkotlin.R
import tra.wor.bookreaderwithkotlin.data.Repositry
import tra.wor.bookreaderwithkotlin.pojo.items
import tra.wor.bookreaderwithkotlin.ui.Adapter
import tra.wor.bookreaderwithkotlin.ui.fragment.FavoriteFragment
import tra.wor.bookreaderwithkotlin.ui.fragment.HomeFragment
import tra.wor.bookreaderwithkotlin.ui.fragment.SearchFragment

class MainActivity : AppCompatActivity() {

    lateinit var adapter: Adapter
    lateinit var recyclerView: RecyclerView
    lateinit var dataList: List<items>

    lateinit var repositry: Repositry
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        repositry = ViewModelProviders.of(this).get(Repositry::class.java)
        recyclerView = findViewById(R.id.rec)
        recyclerView.layoutManager = LinearLayoutManager(this)


         repositry.getdata().observe(this@MainActivity, Observer { item ->
                adapter.setlist(item)
                adapter.notifyDataSetChanged()
                recyclerView.adapter = adapter
            })



        adapter = Adapter(this)
         */
        supportFragmentManager.beginTransaction().replace(R.id.framelayout,HomeFragment()).commit()

        chipNavigationBar.setOnItemSelectedListener(object :
            ChipNavigationBar.OnItemSelectedListener {
            override fun onItemSelected(id: Int) {
                selectfragment(id);
            }

        }

        )
    }

    private fun selectfragment(id: Int) {
        var fragment : Fragment? =null
        if (id==R.id.home)
            fragment=HomeFragment()
        else if (id==R.id.search)
            fragment=SearchFragment()
        else if (id==R.id.favorite)
            fragment=FavoriteFragment()

        if (fragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.framelayout,fragment).commit()
        }
    }

}


