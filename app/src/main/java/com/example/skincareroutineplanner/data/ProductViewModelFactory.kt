package com.example.skincareroutineplanner.data

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//класс-фабрика, позволяющий создавать экзмепляры ViewModel и передавать application
class ProductViewModelFactory(private val application: Application, private val context: Context) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //Проверка подходит ли запрашиваемый класс к нашему ProductViewModel
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            return ProductViewModel(application, context) as T //Создаем ProductViewModel
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}