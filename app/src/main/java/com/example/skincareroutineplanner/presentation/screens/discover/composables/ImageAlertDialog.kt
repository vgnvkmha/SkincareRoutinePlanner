package com.example.skincareroutineplanner.presentation.screens.discover.composables



import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.skincareroutineplanner.data.Product

@Composable
fun ImageAlertDialog(
    product: Product,
    height: Int
) {
    AsyncImage(
        model = product.photo_url,
        contentDescription = "Изображения средства ${product.name}",
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp)
            .clip(RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
    )
}