package tra.wor.bookreaderwithkotlin.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.vinay.shoppinglist.ui.shoppinglist.FavViewModel
import com.vinay.shoppinglist.ui.shoppinglist.FavViewModelFactory
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import tra.wor.bookreaderwithkotlin.R
import tra.wor.bookreaderwithkotlin.pojo.favorite
import tra.wor.bookreaderwithkotlin.ui.adapter.AdapterOfFavorite


class FavoriteFragment() : Fragment() , KodeinAware{
    override val kodein: Kodein by closestKodein()
    lateinit var viewModel: FavViewModel
    private val factory: FavViewModelFactory by instance<FavViewModelFactory>()
    lateinit var adapterOfFavorite: AdapterOfFavorite
    lateinit var list: List<favorite>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inialization()

        viewModel.getAllShoppingItems().observe(viewLifecycleOwner, Observer { item ->
            Log.d(Companion.TAG, "onViewCreated: "+item.size)
            list=item
            adapterOfFavorite.setlist(item)
            rec_fav.adapter = adapterOfFavorite
            ItemTouchHelper(simpleCallback).attachToRecyclerView(rec_fav)

        })
    }

    private fun inialization() {
        viewModel = ViewModelProvider(this, factory).get(FavViewModel::class.java)
        adapterOfFavorite = context?.let {
            AdapterOfFavorite(
                it
            )
        }!!
        rec_fav.setLayoutManager(
            LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
        )
    }

    companion object {
        private const val TAG = "FavoriteFragment"
    }

    var simpleCallback: ItemTouchHelper.SimpleCallback =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: ViewHolder,
                target: ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
                val info_favorite = favorite(
                    list.get(viewHolder.adapterPosition).image,
                    list.get(viewHolder.adapterPosition).title,
                    list.get(viewHolder.adapterPosition).author,
                    list.get(viewHolder.adapterPosition).des
                )
              //  info_favorite.setId(list.get(viewHolder.adapterPosition).getId())
                viewModel.delete(info_favorite)
                adapterOfFavorite.notifyDataSetChanged()
            }
        }

}



