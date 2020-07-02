package tra.wor.bookreaderwithkotlin.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class items(
    @SerializedName("volumeInfo")
    @Expose
    var volumeInfo: volumeInfo
    )


