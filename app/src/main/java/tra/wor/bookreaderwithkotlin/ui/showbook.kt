package tra.wor.bookreaderwithkotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import tra.wor.bookreaderwithkotlin.R

class showbook : AppCompatActivity() {
    lateinit var image:String
    lateinit var des:String
    lateinit var imageView: ImageView
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showbook)
        imageView=findViewById(R.id.header_image)
        textView=findViewById(R.id.textView)

        image=intent.getStringExtra("image").toString()
        des=intent.getStringExtra("des").toString()

        Picasso.get().load(image).into(imageView)
        textView.text=des
    }
}