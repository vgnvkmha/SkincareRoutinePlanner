package com.example.skincareroutineplanner.presentation.screens.settings.composables.myProducts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.skincareroutineplanner.presentation.screens.discover.composables.ImageAlertDialog
import com.example.skincareroutineplanner.ui.theme.OnPrimaryContainer
import com.example.skincareroutineplanner.ui.theme.OnPrimaryContainerDark
import com.example.skincareroutineplanner.ui.theme.PrimaryContainer
import com.example.skincareroutineplanner.ui.theme.PrimaryContainerDark
import com.example.skincareroutineplanner.ui.theme.Surface
import com.example.skincareroutineplanner.ui.theme.mainFontFamily


@Composable
fun MyProducts(
    productViewModel: ProductViewModel,
    lambdaNavigateToSearch: () -> Unit
) {
    val myProducts by productViewModel.userProducts

    var showDialog by remember { mutableStateOf(false) }
    var selectedProduct by remember { mutableStateOf<Product?>(null) }

    LaunchedEffect(Unit) {
        productViewModel.getAllProducts()
    }

    if (myProducts.isNotEmpty()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .height(720.dp)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.Absolute.Center
        ) {
            items(myProducts, key = { it.id }) { product ->
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
                    Column(
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
    }
    else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(720.dp)
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Ваши средства не найдены :(",
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = lambdaNavigateToSearch,
                colors = ButtonColors(
                    containerColor = PrimaryContainer,
                    contentColor = OnPrimaryContainer,
                    disabledContainerColor = PrimaryContainerDark,
                    disabledContentColor = OnPrimaryContainerDark
                ),
            ) {
                Text(
                    text = "Добавить средство",
                    fontFamily = mainFontFamily,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black
                )
            }
        }
    }

    if (showDialog && selectedProduct != null) {
        MyProductsAlertDialog(
            product = selectedProduct!!,
            showDialog = {showDialog = false},
            deleteProduct = {productViewModel.deleteProduct(selectedProduct!!.id)}
        )
    }
}
