package tra.wor.bookreaderwithkotlin.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import tra.wor.bookreaderwithkotlin.pojo.items
import tra.wor.bookreaderwithkotlin.pojo.programming

interface apiinterface {
    @GET("volumes")
    fun getposts(@Query("q")q:String): Call<programming>
}