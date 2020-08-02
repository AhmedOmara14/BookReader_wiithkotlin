package tra.wor.bookreaderwithkotlin.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

class programming(
    @SerializedName("items")
    @Expose
    var items: ArrayList<items?>
) {

   fun getitem(): ArrayList<items?> {
       return items
   }
}