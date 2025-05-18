@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.skincareroutineplanner.presentation.screens.discover.composables


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skincareroutineplanner.data.Product
import com.example.skincareroutineplanner.ui.theme.OnPrimaryContainer
import com.example.skincareroutineplanner.ui.theme.OnSecondaryContainer
import com.example.skincareroutineplanner.ui.theme.OnSurface
import com.example.skincareroutineplanner.ui.theme.mainFontFamily



@Composable
fun CustomAlertDialog(
    product: Product,
    showDialog: () -> Unit,
    saveProduct: (Product) -> Unit
) {
    BasicAlertDialog(
        onDismissRequest = showDialog
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 6.dp,
            color = com.example.skincareroutineplanner.ui.theme.Surface,
            modifier = Modifier
                .fillMaxWidth()
                .height(520.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = product.name,
                    fontSize = 20.sp,
                    fontFamily = mainFontFamily, fontWeight = FontWeight.Black,
                    color = OnSecondaryContainer,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
                ImageAlertDialog(product, height = 280) //Изображение по url
                Text(text = product.ingredients.take(5).joinToString(" ") + "...",
                    fontSize = 16.sp,
                    fontFamily = mainFontFamily,
                    fontWeight = FontWeight.Black,
                    color = OnSurface,
                    modifier = Modifier.align(Alignment.CenterHorizontally))
                Row(modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .background(com.example.skincareroutineplanner.ui.theme.Surface)
                , horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                    IconButton(
                        onClick = {saveProduct(product) }
                    ) {
                        Icon(Icons.Default.Add,
                            contentDescription = "добавить средство"
                        ,tint = OnPrimaryContainer)
                    }
                    Text(text = "Добавить в свою рутину",
                        fontSize = 20.sp,
                        fontFamily = mainFontFamily,
                        fontWeight = FontWeight.Black,
                        color = OnSurface)
                }
            }
        }

    }
}