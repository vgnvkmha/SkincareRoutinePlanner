package com.example.skincareroutineplanner.presentation.screens.discover.composables


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skincareroutineplanner.data.Product
import com.example.skincareroutineplanner.data.ProductViewModel
import com.example.skincareroutineplanner.ui.theme.Background
import com.example.skincareroutineplanner.ui.theme.Surface
import com.example.skincareroutineplanner.ui.theme.mainFontFamily

@Composable
fun RoutineList(
    productViewModel: ProductViewModel,
    onBack: () -> Unit
) {
    val isSearching by productViewModel.isSearching
    val searchProducts by productViewModel.searchProducts
    val myProducts by productViewModel.userProducts

    val productsToShow = if (isSearching) searchProducts else myProducts

    var showDialog by remember { mutableStateOf(false) }
    var selectedProduct by remember { mutableStateOf<Product?>(null) }

    if (productsToShow.isNotEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .height(720.dp)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.Absolute.Center
        ) {
            items(productsToShow, key = { it.id }) { product ->
                Card(
                    modifier = Modifier
                        .size(192.dp),
                    onClick = {
                        selectedProduct = product
                        showDialog = true
                    },
                    colors = CardDefaults.cardColors(
                        containerColor = Surface // Замените на нужный вам цвет
                    ),
                    shape = RoundedCornerShape(16.dp), // по желанию
                    elevation = CardDefaults.cardElevation(8.dp) // по желанию
                ) {
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ImageAlertDialog(product, height = 150)
                        Text(
                            text = product.name,
                            fontSize = 16.sp,
                            fontFamily = mainFontFamily,
                            fontWeight = FontWeight.Black
                        )
                    }
                }

            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(720.dp)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Средства не найдены")
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onBack) {
                Text("Назад")
            }
        }
    }

    if (showDialog && selectedProduct != null) {
        CustomAlertDialog(
            product = selectedProduct!!,
            saveProduct = { productViewModel.saveProduct(selectedProduct!!)},
            showDialog = {showDialog = false}
        )
    }
}