@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.perpustakaankitab.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.perpustakaankitab.data.model.Kitab

@Composable
fun DetailKitabScreen(
    kitab: Kitab
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {

                    Column(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp).height(40.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(kitab.title)
                } }
            )
        },

        bottomBar = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(10.dp).fillMaxWidth()
            ) {
                Text(kitab.pengarang)
            }
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier.padding(paddingValues).fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(kitab.ringkasan, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(40.dp))
            Text(kitab.isiKitab)
        }
    }
}