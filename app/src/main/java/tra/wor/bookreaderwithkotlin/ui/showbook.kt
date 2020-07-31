package tra.wor.bookreaderwithkotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.vinay.shoppinglist.ui.shoppinglist.FavViewModel
import com.vinay.shoppinglist.ui.shoppinglist.FavViewModelFactory
import kotlinx.android.synthetic.main.activity_showbook.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import tra.wor.bookreaderwithkotlin.R
import tra.wor.bookreaderwithkotlin.pojo.favorite

class showbook : AppCompatActivity(), KodeinAware {

    lateinit var image: String
    lateinit var des: String
    lateinit var title: String
    lateinit var author: String

    lateinit var imageView: ImageView
    lateinit var textView: TextView


    private val factory: FavViewModelFactory by instance<FavViewModelFactory>()

    lateinit var viewModel: FavViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showbook)
        imageView = findViewById(R.id.header_image)
        textView = findViewById(R.id.textView)
        image = intent.getStringExtra("image").toString()
        des = intent.getStringExtra("des").toString()
        title = intent.getStringExtra("title").toString()
        if (intent.getStringExtra("author") == null) {
            author = "no author"
        }
        else{
            author = intent.getStringExtra("author").toString()

        }

        Picasso.get().load(image).into(imageView)
        textView.text = des
        viewModel = ViewModelProvider(this, factory).get(FavViewModel::class.java)

        fav.setOnClickListener {
            var favorite: favorite = favorite(image, title, author, des)
            viewModel.upsert(favorite)
            if (viewModel.upsert(favorite) != null) {
                Toast.makeText(this, "save in Favorite", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "not save", Toast.LENGTH_SHORT).show()

            }
        }


    }

    override val kodein by kodein()


}