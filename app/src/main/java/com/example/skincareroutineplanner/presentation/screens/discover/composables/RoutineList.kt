package com.example.skincareroutineplanner.presentation.screens.discover.composables


import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.skincareroutineplanner.data.Product
import com.example.skincareroutineplanner.data.ProductViewModel
import com.example.skincareroutineplanner.data.ProductViewModelFactory
import com.example.skincareroutineplanner.ui.theme.OnSurface
import com.example.skincareroutineplanner.ui.theme.Surface
import com.example.skincareroutineplanner.ui.theme.mainFontFamily

@Composable
fun RoutineList(
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val application = context.applicationContext as Application
    val viewModel: ProductViewModel = viewModel(
        factory = ProductViewModelFactory(application)
    )
    val myProducts: List<Product> = viewModel.getAllProducts()
    var showDialog by remember { mutableStateOf(false) }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .height(750.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        content = {
            items(myProducts) { product ->
                Card(modifier = Modifier.size(192.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Surface
                    ),
                    onClick = {
                        showDialog = true
                    }
                ) {
                    ImageAlertDialog(
                        product
                    )
                    Text(
                        text = product.name, fontFamily = mainFontFamily,
                        fontWeight = FontWeight.Normal, color = OnSurface, modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                }
                if (showDialog) {
                        CustomAlertDialog(
                            product,
                            onBack
                        )
                }
            }
        }
    )
}