package com.example.perpustakaankitab.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perpustakaankitab.data.model.Koleksi
import com.example.perpustakaankitab.data.remote.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import kotlin.collections.emptyList

class KoleksiViewModel: ViewModel(){

    private val _koleksi = mutableStateOf<List<Koleksi>>(emptyList())
    val koleksi: State<List<Koleksi>> = _koleksi

    fun loadKoleksi() {
        viewModelScope.launch {
            try {
                val userId = SupabaseClient.client.auth.currentUserOrNull()?.id
                if (userId != null) {
                    _koleksi.value = SupabaseClient.client
                        .from("koleksi")
                        .select()
                        .decodeList<Koleksi>()
                    Log.d("KoleksiViewModel", "Koleksi: ${_koleksi.value}")
                }
            } catch (e: Exception) {
                Log.e("KoleksiViewModel", "Error koleksi: ${e.message}")
            }
        }
    }

    fun addKoleksi(kitabId: String) {
        viewModelScope.launch {
            try {
                val userId = SupabaseClient.client.auth.currentUserOrNull()?.id
                if (userId != null) {
                    SupabaseClient.client
                        .from("koleksi")
                        .insert(
                            mapOf(
                                "user_id" to userId,
                                "kitab_id" to kitabId
                            )
                        )
                    loadKoleksi()
                }
            } catch (e: Exception) {
                Log.e("KoleksoViewModel", "Error add koleksi: ${e.message}")
            }
        }
    }

    fun removeKoleksi(kitabId: String) {
        viewModelScope.launch {
            try {
                SupabaseClient.client
                    .from("koleksi")
                    .delete {
                        filter {
                            eq("kitab_id", kitabId)
                        }
                    }
                loadKoleksi()
            } catch (e: Exception) {
                Log.e("KoleksiViewModel", "Error remove koleksi: ${e.message}")
            }
        }
    }
}