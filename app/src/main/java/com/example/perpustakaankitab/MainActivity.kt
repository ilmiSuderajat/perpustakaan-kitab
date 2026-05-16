package com.example.perpustakaankitab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.perpustakaankitab.ui.navigation.NavGraph
import com.example.perpustakaankitab.ui.theme.PerpustakaanKitabTheme
import com.example.perpustakaankitab.ui.viewmodel.KitabViewModel
import com.example.perpustakaankitab.ui.viewmodel.KoleksiViewModel
import com.example.perpustakaankitab.ui.viewmodel.ProfileViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PerpustakaanKitabTheme {
                val kitabViewModel: KitabViewModel = viewModel()
                val koleksiViewModel: KoleksiViewModel = viewModel()
                val profileViewModel: ProfileViewModel = viewModel()

                NavGraph(
                    kitabViewModel = kitabViewModel,
                    koleksiViewModel = koleksiViewModel,
                    profileViewModel = profileViewModel
                )
            }
        }
    }
}

