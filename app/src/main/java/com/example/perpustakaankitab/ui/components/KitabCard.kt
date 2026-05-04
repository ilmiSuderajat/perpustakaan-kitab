package com.example.perpustakaankitab.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.perpustakaankitab.data.model.Kitab


@Composable
fun KitabCard(
    kitab: Kitab,
    onclick: () -> Unit
){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable{ onclick() }
    ) {
        Column(
        modifier = Modifier.padding(20.dp)
        ){

            Text(kitab.title, style = MaterialTheme.typography.titleSmall, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(5.dp))
            Text(kitab.ringkasan, style = MaterialTheme.typography.bodySmall, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Pengarang : ${kitab.pengarang}", style = MaterialTheme.typography.bodySmall, fontSize = 12.sp)
        }
    }
}