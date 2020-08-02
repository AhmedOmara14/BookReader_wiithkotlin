package tra.wor.bookreaderwithkotlin.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_sports_book.view.*
import tra.wor.bookreaderwithkotlin.R
import tra.wor.bookreaderwithkotlin.pojo.sportsbook
import tra.wor.bookreaderwithkotlin.ui.showbookofsports

class AdapterOfSports(var context: Context) : RecyclerView.Adapter<AdapterOfSports.viewholder>() {

    lateinit var list_sports_book: List<sportsbook>

    fun setlist(list: List<sportsbook>) {
        list_sports_book = list
    }

    class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun find(sportsbook: sportsbook) {
            Picasso.get().load(sportsbook.image_Book).into(itemView.image_sports)
            itemView.name_sports.text = sportsbook.title_book
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_sports_book, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list_sports_book.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val items = list_sports_book[position]
        holder.find(items)

        holder.itemView.setOnClickListener {
            val intent=Intent(context,
                showbookofsports::class.java)
            intent.putExtra("name",items.title_book)
            context.startActivity(intent)
        }
    }
}