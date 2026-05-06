package com.example.perpustakaankitab.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.perpustakaankitab.ui.screen.DetailKitabScreen
import com.example.perpustakaankitab.ui.screen.KitabScreen
import com.example.perpustakaankitab.ui.screen.LoginScreen
import com.example.perpustakaankitab.ui.viewmodel.KitabViewModel

@Composable
fun NavGraph(
    viewModel: KitabViewModel
){
    val navController = rememberNavController()

    LaunchedEffect(viewModel.isLoggedIn.value) {
        if (viewModel.isLoggedIn.value) {
            navController.navigate("kitabscreen") {
                popUpTo("login") { inclusive = true }
            }
        }
    }
    NavHost(
        navController = navController,
        startDestination = "login"

    ){

            composable("login") {
                LoginScreen(
                    viewModel = viewModel,
                    onLoginSuccess = {}
                )
            }



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