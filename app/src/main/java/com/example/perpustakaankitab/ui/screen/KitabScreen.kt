package com.example.perpustakaankitab.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.perpustakaankitab.ui.components.KitabCard
import com.example.perpustakaankitab.ui.viewmodel.KitabViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KitabScreen(
    viewModel: KitabViewModel,
    onKitabClick: (String) -> Unit
){
    val kitab = viewModel.kitab.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Perpustakaan Kita") }
            )
        }

    ){ paddingValues ->

        LazyColumn(
            modifier = Modifier.padding(paddingValues).padding(16.dp)
        ) {

        items(kitab) { kitab ->
            KitabCard(
                kitab = kitab,
                onclick = {onKitabClick(kitab.id)}
            )

        }
        }
    }
}