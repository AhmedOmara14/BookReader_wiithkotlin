package com.vinay.shoppinglist.data.repositories

import android.os.AsyncTask
import tra.wor.bookreaderwithkotlin.pojo.favorite
import tra.wor.bookreaderwithkotlin.pojo.favoriteDao
import tra.wor.bookreaderwithkotlin.pojo.favoritedatabase

class FavRepository(private val db: favoritedatabase) {
    fun upsert(item: favorite) = db.favoriteDao()?.insert(item)
    fun delete(item: favorite) =
        db.favoriteDao()?.delete(item)

    fun getAllShoppingItems() = db.favoriteDao()?.selectallrecord()


}