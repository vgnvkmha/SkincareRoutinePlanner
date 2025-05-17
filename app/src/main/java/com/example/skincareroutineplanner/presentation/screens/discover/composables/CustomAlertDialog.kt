@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.skincareroutineplanner.presentation.screens.discover.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skincareroutineplanner.data.Product
import com.example.skincareroutineplanner.ui.theme.OnSurface
import com.example.skincareroutineplanner.ui.theme.mainFontFamily



@Composable
fun CustomAlertDialog(
    product: Product,
    onBack: () -> Unit
) {
    BasicAlertDialog(
        onDismissRequest = onBack
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 6.dp,
            color = com.example.skincareroutineplanner.ui.theme.Surface,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(270.dp)
                    .padding(8.dp)
            ) {
                Text(text = product.name,
                    fontSize = 20.sp,
                    fontFamily = mainFontFamily, fontWeight = FontWeight.Black,
                    color = OnSurface,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
                ImageAlertDialog(product) //Изображение по url
                Text(text = product.ingredients.joinToString(" "),
                    fontSize = 16.sp,
                    fontFamily = mainFontFamily,
                    fontWeight = FontWeight.Black,
                    color = OnSurface,
                    modifier = Modifier.align(Alignment.CenterHorizontally))
            }
        }

    }
}