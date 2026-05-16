package com.example.perpustakaankitab.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.perpustakaankitab.ui.components.BottomNavBar
import com.example.perpustakaankitab.ui.components.LoginPrompt
import com.example.perpustakaankitab.ui.screen.DetailKitabScreen
import com.example.perpustakaankitab.ui.screen.KitabScreen
import com.example.perpustakaankitab.ui.screen.KoleksiScreen
import com.example.perpustakaankitab.ui.screen.ProfileScreen
import com.example.perpustakaankitab.ui.viewmodel.KitabViewModel
import com.example.perpustakaankitab.ui.viewmodel.KoleksiViewModel
import com.example.perpustakaankitab.ui.viewmodel.ProfileViewModel

@Composable
fun NavGraph(
    kitabViewModel: KitabViewModel,
    koleksiViewModel: KoleksiViewModel,
    profileViewModel: ProfileViewModel
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "kitabscreen"

    LaunchedEffect(kitabViewModel.isLoggedIn.value) {
        if (kitabViewModel.isLoggedIn.value) {
            navController.navigate("kitabscreen") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    Scaffold(
        bottomBar = {
            if (currentRoute != "login" && !currentRoute.startsWith("detail")) {
                BottomNavBar(
                    currentRoute = currentRoute,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo("kitabscreen") { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "kitabscreen",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("login") {
                LoginPrompt(viewModel = kitabViewModel, onLoginSuccess = {})
            }
            composable("kitabscreen") {
                KitabScreen(
                    kitabViewModel = kitabViewModel,
                    profileViewModel = profileViewModel,
                    onKitabClick = { id -> navController.navigate("detail/$id") }
                )
            }
            composable("koleksiscreen") {
                KoleksiScreen(
                    profileViewModel = profileViewModel,
                    kitabViewModel = kitabViewModel,
                    koleksiViewModel = koleksiViewModel,
                    onKitabClick = { id -> navController.navigate("detail/$id") }
                )
            }
            composable("profilescreen") {
                ProfileScreen( profileViewModel = profileViewModel, kitabViewModel = kitabViewModel, onUpdateName = {})
            }
            composable("detail/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")
                val kitab = kitabViewModel.kitab.value.find { it.id == id }
                if (kitab != null) {
                    DetailKitabScreen(kitab = kitab,
                        koleksiViewModel = koleksiViewModel
                        )
                }
            }
        }
    }
}