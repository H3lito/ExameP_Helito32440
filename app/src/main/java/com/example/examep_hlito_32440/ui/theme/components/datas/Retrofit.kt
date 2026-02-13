package com.example.examep_hlito_32440.ui.theme.components.datas

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// 1. MODELO DA API (O que vem do JSON)
data class ODS(
    val code: Int,
    val title: String, // Verifica no JSON se é 'title', 'name', etc.
    val description: String
)

// 2. INTERFACE
interface ApiService {
    @GET("posts") // Mudar o endpoint (ex: 'products', 'users')
    suspend fun getDados(): List<ODS>
}

// 3. SINGLETON
object RetrofitClient {
    // Mudar URL base (acabar sempre com /)
    private const val BASE_URL = "https://unstats.un.org/sdgapi/v1/sdg/Goal/List?includechildren=true"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}