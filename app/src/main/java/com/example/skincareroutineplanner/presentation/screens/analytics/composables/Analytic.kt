package com.example.skincareroutineplanner.presentation.screens.analytics.composables

import com.example.skincareroutineplanner.data.ProductViewModel

class Analytic(
    viewModel: ProductViewModel
) {
    val allProducts = viewModel.getAllProducts()


}