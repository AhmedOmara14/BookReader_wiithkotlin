package tra.wor.bookreaderwithkotlin.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_show_sports_book.view.*
import tra.wor.bookreaderwithkotlin.R
import tra.wor.bookreaderwithkotlin.pojo.favorite
import tra.wor.bookreaderwithkotlin.ui.showbook

class AdapterOfFavorite(var context: Context) :
    RecyclerView.Adapter<AdapterOfFavorite.viewholder>() {
    lateinit var list: List<favorite>
    fun setlist(data: List<favorite>) {
        list = data
    }




    class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun find(item: favorite) {
            Picasso.get().load(item.image).into(itemView.image_show_book)
            //

            if (item.author != null) {
                itemView.title_show_book.text = item.author.toString()
            } else
                itemView.title_show_book.text = "No Author"
            itemView.name_show_book.text = item.title
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): viewholder {
        return viewholder(
            LayoutInflater.from(
                context
            ).inflate(
                R.layout.layout_show_sports_book,
                parent,
                false
            )
        )
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val items = list[position]
        holder.find(items)
        var image: String
        var title: String
        var des: String
        var author: String
        holder.itemView.setOnClickListener {
            val intent = Intent(context, showbook::class.java)
            intent.putExtra("image", items.image.toString())
            if (items.title == null) {
                intent.putExtra("name", "no title")
                title = "no title"
            } else {
                title = items.title
            }

            if (items.author == null) {
                intent.putExtra("author", "no author")
                author = "no author"
            } else {
                author = items.author.toString()
                intent.putExtra("author", author)
            }
            if (items.title == null) {
                intent.putExtra("title", "no title")
            } else {
                title = items.title.toString()
                intent.putExtra("title", title)
            }

            if (items.des == null) {
                intent.putExtra("des", "No Description")
                des = "No Description"
            } else {
                intent.putExtra("des", items.des.toString())
                des = items.des
            }
            context.startActivity(intent)
        }


    }
}