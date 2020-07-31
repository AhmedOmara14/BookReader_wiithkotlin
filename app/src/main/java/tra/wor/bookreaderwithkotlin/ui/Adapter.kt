package tra.wor.bookreaderwithkotlin.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_programing_book.view.*
import tra.wor.bookreaderwithkotlin.R
import tra.wor.bookreaderwithkotlin.pojo.items

class Adapter (  var conxt: Context): RecyclerView.Adapter<Adapter.viewholder>() {

     lateinit var list: List<items>

    fun setlist(data: List<items>){
       list=data
    }
    class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun find(item: items){
            Picasso.get().load(item.volumeInfo.imageLinks.thumbnail).into(itemView.image_prog)
            itemView.title_prog.text=item.volumeInfo.authors.get(0).toString()
            itemView.name_prog.text=item.volumeInfo.title



        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(
            LayoutInflater.from(
                conxt
            ).inflate(
                R.layout.layout_programing_book,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val items=list[position]
        holder.find(items)
        holder.itemView.setOnClickListener {
            val intent = Intent(conxt, showbook::class.java)
             intent.putExtra("image",items.volumeInfo.imageLinks.thumbnail.toString())
            intent.putExtra("title",items.volumeInfo.title.toString())
            intent.putExtra("author",items.volumeInfo.authors.get(0).toString())
             if (items.volumeInfo.description==null){
                 intent.putExtra("des","No Description")
             }
            else {
                 intent.putExtra("des",
                     items.volumeInfo.description.toString())
             }

            conxt.startActivity(intent)
        }
    }
}