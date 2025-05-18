@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.skincareroutineplanner.presentation.screens.discover.composables


import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.skincareroutineplanner.ui.theme.OnPrimaryContainer
import com.example.skincareroutineplanner.ui.theme.PrimaryContainer
import com.example.skincareroutineplanner.ui.theme.Surface
import com.example.skincareroutineplanner.ui.theme.mainFontFamily
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.lifecycle.lifecycleScope
import com.example.skincareroutineplanner.data.ProductViewModel
import kotlinx.coroutines.launch


@Composable
fun AddScreenTopAppBar(
    lambdaBack: () -> Unit, //лямбда возвращения к предыдущему экрану в стэк трейсе
    viewModel: ProductViewModel
) {
    val context = LocalContext.current //текущий контекст для вызыва lifecycleScope
    val activity = context as? ComponentActivity
    val isSearching by viewModel.isSearching
    var searchText by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
//    val products by viewModel.products
    TopAppBar(
        colors = TopAppBarColors(
            containerColor = PrimaryContainer,
            titleContentColor = OnPrimaryContainer,
            actionIconContentColor = OnPrimaryContainer,
            scrolledContainerColor = Surface,
            navigationIconContentColor = OnPrimaryContainer
        ),
        title = {
            //если пользователь открыл клавиатуру для поиска
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                singleLine = true,
                placeholder = { Text("Введите название") },
                keyboardActions = KeyboardActions(
                    onDone = { //Когда пользователь скрыл клавиатуру
                        keyboardController?.hide()

//                            else {
//                                Toast.makeText(context, "Введите название средства",
//                                    Toast.LENGTH_LONG).show()
//                            }
                    }
                )
            )
            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
                keyboardController?.show()
            }

        },
        navigationIcon = {  //Кнопка возвращение на предыдущий экран
            IconButton(onClick = {
                if (isSearching) {
                    viewModel.clearSearch()
                    searchText = ""
                    keyboardController?.hide()
                } else {
                    lambdaBack()
                }
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Назад"
                )
            }
        },
        actions = { //Кнопка поиска
            IconButton(onClick = {
                if (searchText.isNotBlank()) {
                    viewModel.startSearch() //Начинаем поиск, чтобы поле isSearching = true
                    activity?.lifecycleScope?.launch {
                        viewModel.fetchProducts(searchText)
                    }
                }

            }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Начать поиск"
                )
            }
        }
    )
}