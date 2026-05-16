package com.example.perpustakaankitab.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perpustakaankitab.data.model.Profile
import com.example.perpustakaankitab.data.remote.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch


class ProfileViewModel: ViewModel(){
    
    private val _profile = mutableStateOf<Profile?>(null)
    val profile : State<Profile?> = _profile

    init {
        loadProfile()
    }

     fun loadProfile(){
        viewModelScope.launch {
            try {
                _profile.value = SupabaseClient.client
                    .from("profiles")
                    .select()
                    .decodeSingle<Profile>()
                Log.d("ProfileViewmodel", "Data : ${_profile.value}")
            } catch (e: Exception) {
                Log.e("ProfileViewModel", "Error: ${e.message}")
            }
        }
    }

    fun updateProfile(nama: String, tema: String){
        viewModelScope.launch {
            try {
                val userId = SupabaseClient.client.auth.currentUserOrNull()?.id
                if (userId != null){
                    SupabaseClient.client
                        .from("profiles")
                        .upsert(
                            mapOf(
                                "id" to userId,
                                "nama" to nama,
                                "tema" to tema
                            )
                        ) {
                            filter {
                                eq(
                                    "id", userId
                                )
                            }
                        }
                    loadProfile()
                }

            } catch (e: Exception) {

                Log.e("ProfileViewModel", "Error Update : ${e.message}")

            }
        }
    }


    
}