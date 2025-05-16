@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.skincareroutineplanner.presentation.screens.discover.composables


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
import com.example.skincareroutineplanner.data.KtorClient
import com.example.skincareroutineplanner.data.Product
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json


@Composable
fun AddScreenTopAppBar(
    lambdaBack: () -> Unit
) {
    val context = LocalContext.current
    val activity = context as? ComponentActivity
    var isSearching by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    TopAppBar(
        colors = TopAppBarColors(
            containerColor = PrimaryContainer,
            titleContentColor = OnPrimaryContainer,
            actionIconContentColor = OnPrimaryContainer,
            scrolledContainerColor = Surface,
            navigationIconContentColor = OnPrimaryContainer
        ),
        title = {
            if (isSearching) {
                TextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    singleLine = true,
                    placeholder = { Text("Введите название") },
                    keyboardActions = KeyboardActions(
                        onDone = {
                            keyboardController?.hide()
                            if (searchText.isNotBlank()) {
//                                val ip = "172.20.10.8"
                                val localIP = "10.0.2.2"
                                activity?.lifecycleScope?.launch {
                                    try {
                                        val products:List<Product> = KtorClient.client.
                                        get("http://$localIP:8080/api/products").body()
                                        val product = products.filter { searchText in it.name}
                                        if (product.isNotEmpty()) {
                                            Toast.makeText(context, "$product", Toast.LENGTH_LONG).show()
                                        }
                                        else {
                                            Toast.makeText(context,
                                                "Введите корректное название",
                                                Toast.LENGTH_LONG).show()
                                        }
                                    }
                                    catch (e:Exception) {
                                        Toast.makeText(
                                            context,
                                            "Ошибка при соединении с сервером, попробуйте позже",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        e.printStackTrace()
                                    }

                                }
                            }
                            else {
                                Toast.makeText(context, "Введите название средства",
                                    Toast.LENGTH_LONG).show()
                            }
                        }
                    )
                )
                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                    keyboardController?.show()
                }
            } else {
                Text(
                    text = "Найти средство",
                    modifier = Modifier.padding(16.dp),
                    fontFamily = mainFontFamily,
                    fontWeight = FontWeight.Black
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                if (isSearching) {
                    isSearching = false
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
        actions = {
            IconButton(onClick = {
                isSearching = true
            }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Начать поиск"
                )
            }
        }
    )
}