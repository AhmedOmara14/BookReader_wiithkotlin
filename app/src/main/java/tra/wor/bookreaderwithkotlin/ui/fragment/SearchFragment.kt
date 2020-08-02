package tra.wor.bookreaderwithkotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_search.*
import tra.wor.bookreaderwithkotlin.R
import tra.wor.bookreaderwithkotlin.data.Repositry
import tra.wor.bookreaderwithkotlin.pojo.items
import tra.wor.bookreaderwithkotlin.ui.adapter.AdapterOfShowSportsBook


class SearchFragment : Fragment() {

    lateinit var adapterOfShowSportsBook: AdapterOfShowSportsBook
    lateinit var repositry: Repositry
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inalizeation()
        val list: ArrayList<items> = ArrayList<items>()

        btn_title.setOnClickListener {
            list.clear()
            repositry.getdata_book("programming").observe(viewLifecycleOwner, Observer { item->
               var x = 0
               while (x < item.size) {
                   if (item.get(x).volumeInfo.title.toString().contains(search_edittext.text.toString().toLowerCase())){
                        list.add(item.get(x))
                       adapterOfShowSportsBook.setlist(list)
                       recycler_search.adapter=adapterOfShowSportsBook
                   }
                   x++
               }
           })


        }
        btn_author.setOnClickListener {
            list.clear()
            repositry.getdata_book("programming").observe(viewLifecycleOwner, Observer { item->
                var x = 0
                while (x < item.size) {
                    if (item.get(x).volumeInfo.authors.get(0).toString().contains(search_edittext.text.toString().toLowerCase())){
                        list.add(item.get(x))
                        adapterOfShowSportsBook.setlist(list)
                        recycler_search.adapter=adapterOfShowSportsBook
                    }
                    x++
                }
            })


        }
    }

    private fun inalizeation() {
        adapterOfShowSportsBook = context?.let {
            AdapterOfShowSportsBook(
                it
            )
        }!!

        recycler_search.setLayoutManager(
            LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
        )
        repositry = ViewModelProviders.of(this).get(Repositry::class.java)

    }

}