package com.vinay.shoppinglist.ui.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vinay.shoppinglist.data.repositories.FavRepository


class FavViewModelFactory(
    private val repository: FavRepository
): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavViewModel(repository) as T
    }
}