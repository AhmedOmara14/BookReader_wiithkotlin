package tra.wor.bookreaderwithkotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import tra.wor.bookreaderwithkotlin.R
import tra.wor.bookreaderwithkotlin.data.Repositry
import tra.wor.bookreaderwithkotlin.pojo.sportsbook
import tra.wor.bookreaderwithkotlin.ui.adapter.Adapter
import tra.wor.bookreaderwithkotlin.ui.adapter.AdapterOfSports


class HomeFragment : Fragment() {

    lateinit var adapter: Adapter
    lateinit var repositry: Repositry
    lateinit var adapterOfSports: AdapterOfSports
    lateinit var sportsbook: sportsbook
    var listsportsbook: java.util.ArrayList<sportsbook> = java.util.ArrayList<sportsbook>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        intalizeation()

        repositry.getdata_book("programming").observe(viewLifecycleOwner, Observer { items ->
            adapter = context?.let {
                Adapter(
                    it
                )
            }!!
            adapter.setlist(items)
            recycler_programming.adapter = adapter;
        })
        additem()
        adapterOfSports = context?.let {
            AdapterOfSports(
                it
            )
        }!!
        adapterOfSports.setlist(listsportsbook)
        recycler_sports_book.adapter = adapterOfSports

        search_items.setOnClickListener {

            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.framelayout,SearchFragment())?.commit()

        }

    }

    private fun additem() {
        listsportsbook.add(sportsbook(R.drawable.basketball, "Basketball"))
        listsportsbook.add(sportsbook(R.drawable.tennis, "tennis"))
        listsportsbook.add(sportsbook(R.drawable.volleyball, "volleyball"))
        listsportsbook.add(sportsbook(R.drawable.soccer, "football"))

    }


    private fun intalizeation() {
        repositry = ViewModelProviders.of(this).get(Repositry::class.java)

        recycler_programming.setLayoutManager(
            LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )
        recycler_sports_book.setLayoutManager(
            LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )

    }

}