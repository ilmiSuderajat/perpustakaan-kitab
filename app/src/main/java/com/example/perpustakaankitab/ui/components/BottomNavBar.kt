package com.example.perpustakaankitab.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BottomNavBar(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == "kitabscreen",
            onClick = { onNavigate("kitabscreen") },
            icon = { Icon(Icons.Default.Home, contentDescription = "Kitab") },
            label = { Text("Kitab") }
        )
        NavigationBarItem(
            selected = currentRoute == "koleksiscreen",
            onClick = { onNavigate("koleksiscreen") },
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Koleksi") },
            label = { Text("Koleksi") }
        )
        NavigationBarItem(
            selected = currentRoute == "profilescreen",
            onClick = { onNavigate("profilescreen") },
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") }
        )
    }
}