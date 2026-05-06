package com.example.perpustakaankitab.data.remote

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.googleNativeLogin
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClient {
    val client = createSupabaseClient(
        supabaseUrl = "https://ilxqawwsfimekdpgddos.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImlseHFhd3dzZmltZWtkcGdkZG9zIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Nzc4NzkwNTQsImV4cCI6MjA5MzQ1NTA1NH0.9lhuu1P7ekBsMgi7Twf7zLsU9v1cwSgUo4IhCmGBBLM",
    ) {
        install(Postgrest)
        install(Auth)
        install(ComposeAuth){
            //webclient id
            googleNativeLogin("728824134693-7ecrai1cm6ei9ai4cm82trlt3str1kme.apps.googleusercontent.com")
        }
    }
}