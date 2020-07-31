package com.vinay.shoppinglist.ui.shoppinglist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.vinay.shoppinglist.data.repositories.FavRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tra.wor.bookreaderwithkotlin.pojo.favorite

class FavViewModel(
    private val repository: FavRepository
) : ViewModel() {
    fun upsert(item: favorite) {
        repository.upsert(item)
    }

     fun delete(item: favorite){
        repository.delete(item)
    }

    fun getAllShoppingItems() = repository.getAllShoppingItems()

}