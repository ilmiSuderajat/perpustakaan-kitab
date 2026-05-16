package com.example.perpustakaankitab.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.perpustakaankitab.ui.components.LoginPrompt
import com.example.perpustakaankitab.ui.viewmodel.KitabViewModel
import com.example.perpustakaankitab.ui.viewmodel.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    kitabViewModel: KitabViewModel,
    profileViewModel: ProfileViewModel,
    onUpdateName: () -> Unit
) {
    val profile = profileViewModel.profile.value
    val temaList = listOf("Semua", "Fikih", "Tasawuf", "Tauhid", "Nahwu", "Hadits", "Tafsir", "Akhlak")
    var selectedTema by remember { mutableStateOf(profile?.tema ?: "Semua") }
    var showDialog by remember { mutableStateOf(false) }
    var namaInput by remember { mutableStateOf(profile?.nama ?: "") }

    LaunchedEffect(Unit) {
        if (kitabViewModel.isLoggedIn.value){
            profileViewModel.loadProfile()
        }
    }

    if (kitabViewModel.isLoggedIn.value){
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Profile Saya") },
                    actions = {
                        Column(
                            modifier = Modifier.padding(10.dp)
                        ) {
                            IconButton(
                                onClick = { kitabViewModel.logout()}
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ExitToApp,
                                    contentDescription = "Logout",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                            Text("Keluar")
                        }

                    }
                )

            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(40.dp))

                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.size(120.dp),
                    tint = MaterialTheme.colorScheme.primary
                )

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = profile?.nama ?: "User",
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Box(
                        modifier = Modifier
                            .minimumInteractiveComponentSize()
                            .clickable(
                                onClick = { showDialog = true },
                                indication = ripple(
                                    bounded = false,
                                    radius = 20.dp
                                ),
                                interactionSource = remember { MutableInteractionSource() }
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null,
                            modifier = Modifier.size(30.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                }

                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        title = { Text("Edit Nama") },
                        text = {
                            OutlinedTextField(
                                value = namaInput,
                                onValueChange = { namaInput = it },
                                label = { Text("Nama") }
                            )
                        },
                        confirmButton = {
                            Button(onClick = {
                                profileViewModel.updateProfile(namaInput, profile?.tema ?: "Semua")
                                showDialog = false
                            }) {
                                Text("Simpan")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showDialog = false }) {
                                Text("Batal")
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text("Pilih Tema:", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    temaList.forEach { tema ->
                        FilterChip(
                            selected = selectedTema == tema,
                            onClick = { selectedTema = tema },
                            label = { Text(tema) },
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = { profileViewModel.updateProfile(profile?.nama ?: "", selectedTema) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Simpan")
                }

            }
        }
    }else {
        LoginPrompt(viewModel = kitabViewModel, onLoginSuccess = { profileViewModel.loadProfile() })
    }

}



