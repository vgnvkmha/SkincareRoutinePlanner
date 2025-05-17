package com.example.skincareroutineplanner.data

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//класс-фабрика, позволяющий создавать экзмепляры ViewModel и передавать application
class ProductViewModelFactory(private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //Проверка подходит ли запрашиваемый класс к нашему ProductViewModel
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            return ProductViewModel(application) as T //Создаем ProductViewModel
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}