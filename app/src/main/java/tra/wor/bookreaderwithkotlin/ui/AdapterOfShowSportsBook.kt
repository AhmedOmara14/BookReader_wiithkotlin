package tra.wor.bookreaderwithkotlin.ui

import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_programing_book.view.*
import kotlinx.android.synthetic.main.layout_show_sports_book.view.*
import tra.wor.bookreaderwithkotlin.R
import tra.wor.bookreaderwithkotlin.data.Repositry
import tra.wor.bookreaderwithkotlin.pojo.favorite
import tra.wor.bookreaderwithkotlin.pojo.items

class AdapterOfShowSportsBook(var context: Context) :
    RecyclerView.Adapter<AdapterOfShowSportsBook.viewholder>() {
    lateinit var list: List<items>
    lateinit var repositry1: Repositry
    lateinit var application: Application
    fun setlist(data: List<items>) {
        list = data
    }

    fun setRepositry(repositry: Repositry) {
        this.repositry1 = repositry
    }

    fun setapp(application: Application) {
        this.application = application
    }


    class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun find(item: items) {
            Picasso.get().load(item.volumeInfo.imageLinks.thumbnail).into(itemView.image_show_book)
            //

            if (item.volumeInfo.authors != null) {
                itemView.title_show_book.text = item.volumeInfo.authors.get(0).toString()
            } else
                itemView.title_show_book.text = "No Author"
            itemView.name_show_book.text = item.volumeInfo.title
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterOfShowSportsBook.viewholder {
        return AdapterOfShowSportsBook.viewholder(
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

    override fun onBindViewHolder(holder: AdapterOfShowSportsBook.viewholder, position: Int) {
        val items = list[position]
        holder.find(items)
        var image: String
        var title: String
        var des: String
        var author: String
        holder.itemView.setOnClickListener {
            val intent = Intent(context, showbook::class.java)
            intent.putExtra("image", items.volumeInfo.imageLinks.thumbnail.toString())
            if (items.volumeInfo.title == null) {
                intent.putExtra("name", "no title")
                title = "no title"
            } else {
                title = items.volumeInfo.title
            }

            if (items.volumeInfo.authors == null) {
                intent.putExtra("author", "no author")
                author = "no author"
            } else {
                author = items.volumeInfo.authors.get(0).toString()
                intent.putExtra("author", author)
            }
            if (items.volumeInfo.title == null) {
                intent.putExtra("title", "no title")
            } else {
                title = items.volumeInfo.title.toString()
                intent.putExtra("title", title)
            }

            if (items.volumeInfo.description == null) {
                intent.putExtra("des", "No Description")
                des = "No Description"
            } else {
                intent.putExtra("des", items.volumeInfo.description.toString())
                des = items.volumeInfo.description
            }
            context.startActivity(intent)
        }


    }
}