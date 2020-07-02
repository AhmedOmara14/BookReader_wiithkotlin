package tra.wor.bookreaderwithkotlin.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class imageLink (
    @SerializedName("thumbnail")
    @Expose
    public var thumbnail: String
)