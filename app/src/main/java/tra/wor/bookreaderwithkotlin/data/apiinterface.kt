package tra.wor.bookreaderwithkotlin.data

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import tra.wor.bookreaderwithkotlin.pojo.items
import tra.wor.bookreaderwithkotlin.pojo.programming
import java.util.*

interface apiinterface {
    @GET("volumes")
    fun getposts(@Query("q")q:String):Observable<programming>
}