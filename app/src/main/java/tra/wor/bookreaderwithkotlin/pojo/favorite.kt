package tra.wor.bookreaderwithkotlin.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_tab")
class favorite() {
    lateinit var image: String

    @PrimaryKey
    lateinit var title: String

    lateinit var author: String

    lateinit var des: String

    constructor(image: String, title: String, author: String, des: String) : this() {
        this.image = image
        this.title = title
        this.author = author
        this.des = des
    }


}

