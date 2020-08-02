package tra.wor.bookreaderwithkotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_showbookofsports.*
import tra.wor.bookreaderwithkotlin.R
import tra.wor.bookreaderwithkotlin.data.Repositry
import tra.wor.bookreaderwithkotlin.ui.adapter.AdapterOfShowSportsBook

class showbookofsports : AppCompatActivity() {
    lateinit var adapterOfShowSportsBook: AdapterOfShowSportsBook
    lateinit var repositry: Repositry
    lateinit var categry_sports: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showbookofsports)

        inalization()
        categry_sports = intent.getStringExtra("name").toString()
        repositry.getdata_book(categry_sports).observe(this, Observer { item ->
            adapterOfShowSportsBook.setlist(item)
            recycler_show_sports_book.adapter = adapterOfShowSportsBook
        })
        back.setOnClickListener {
            finish()
        }
        name_sports_show.text = categry_sports
    }


    private fun inalization() {
        repositry = ViewModelProviders.of(this).get(Repositry::class.java)
        adapterOfShowSportsBook =
            AdapterOfShowSportsBook(this)
            adapterOfShowSportsBook.setRepositry(repositry)
        adapterOfShowSportsBook.setapp(application)
        recycler_show_sports_book.setLayoutManager(
            LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
        )
    }

}