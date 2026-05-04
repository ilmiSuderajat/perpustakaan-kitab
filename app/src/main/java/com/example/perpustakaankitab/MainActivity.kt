package com.example.perpustakaankitab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.perpustakaankitab.ui.navigation.NavGraph
import com.example.perpustakaankitab.ui.theme.PerpustakaanKitabTheme
import com.example.perpustakaankitab.ui.viewmodel.KitabViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PerpustakaanKitabTheme {
                val viewModel: KitabViewModel = viewModel()
                NavGraph(
                    viewModel = viewModel
                )
            }
        }
    }
}

