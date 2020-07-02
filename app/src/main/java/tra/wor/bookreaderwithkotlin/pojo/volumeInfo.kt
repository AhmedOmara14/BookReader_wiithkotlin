package tra.wor.bookreaderwithkotlin.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class volumeInfo(
    @SerializedName("title")
    @Expose var title: String,

    @SerializedName("authors")
    @Expose
     val authors: ArrayList<*>,

    @SerializedName("description")
    @Expose
     val description: String,

    @SerializedName("imageLinks")
    @Expose
    var imageLinks: imageLink
)