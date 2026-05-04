package com.example.perpustakaankitab.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.perpustakaankitab.ui.screen.DetailKitabScreen
import com.example.perpustakaankitab.ui.screen.KitabScreen
import com.example.perpustakaankitab.ui.viewmodel.KitabViewModel

@Composable
fun NavGraph(
    viewModel: KitabViewModel
){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "kitabscreen"

    ){
        composable("kitabscreen"){
            KitabScreen(
                viewModel = viewModel,
                onKitabClick = {id -> navController.navigate("detail/$id")}
            )
        }

        composable("detail/{id}"){ backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            val kitab = viewModel.kitab.value.find { it.id == id }

            if (kitab != null){
                DetailKitabScreen(
                    kitab = kitab
                )
            }

        }

    }
}