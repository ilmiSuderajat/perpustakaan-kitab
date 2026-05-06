package com.example.perpustakaankitab.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perpustakaankitab.data.model.Kitab
import com.example.perpustakaankitab.data.remote.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch

class KitabViewModel : ViewModel(){

    private val _kitab = mutableStateOf<List<Kitab>>(emptyList())
    val kitab : State<List<Kitab>> = _kitab

    // 1. State untuk cek apakah user sudah login
    var isLoggedIn = mutableStateOf(false)
        private set

    init {
        loadKitab()
        checkSession()
    }

    private fun loadKitab(){
        viewModelScope.launch {
            try {
                _kitab.value = SupabaseClient.client
                    .from("kitab")
                    .select()
                    .decodeList<Kitab>()
                Log.d("KitabViewModel", "Data : ${_kitab.value}")
            } catch (e: Exception){
                Log.e("KitabViewModel", "Error: ${e.message}")
            }
        }
    }

    // 2. Fungsi untuk cek status login saat app dibuka
    fun checkSession() {
        viewModelScope.launch {
            try {
                val session = SupabaseClient.client.auth.currentSessionOrNull()
                isLoggedIn.value = session != null
            } catch (e: Exception) {
                isLoggedIn.value = false
            }
        }
    }

}