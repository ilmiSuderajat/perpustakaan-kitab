@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.perpustakaankitab.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.perpustakaankitab.ui.components.KitabCard
import com.example.perpustakaankitab.ui.components.LoginPrompt
import com.example.perpustakaankitab.ui.viewmodel.KitabViewModel
import com.example.perpustakaankitab.ui.viewmodel.KoleksiViewModel
import com.example.perpustakaankitab.ui.viewmodel.ProfileViewModel

@Composable
fun KoleksiScreen(
    profileViewModel: ProfileViewModel,
    kitabViewModel: KitabViewModel,
    koleksiViewModel: KoleksiViewModel,
    onKitabClick: (String) -> Unit
){

    // Di KoleksiScreen
    val semuaKitab = kitabViewModel.kitab.value
    val koleksiIds = koleksiViewModel.koleksi.value.map { it.kitabId }
    val kitabKoleksi = semuaKitab.filter { it.id in koleksiIds }

    LaunchedEffect(Unit) {
        koleksiViewModel.loadKoleksi()
    }
    if (kitabViewModel.isLoggedIn.value){
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("Koleksi Saya")
                    }
                )
            }
        ) { paddingValues ->

            LazyColumn(
                modifier = Modifier.padding(paddingValues)
            ) {

                items(kitabKoleksi){ kitab ->

                    KitabCard(
                        kitab = kitab,
                        onclick = {onKitabClick(kitab.id)}
                    )

                }

            }
        }
    } else {
        LoginPrompt(viewModel = kitabViewModel, onLoginSuccess = { profileViewModel.loadProfile() })
    }


}