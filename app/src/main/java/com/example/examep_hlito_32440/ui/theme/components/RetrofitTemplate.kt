package com.example.examep_hlito_32440.ui.theme.components
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// --- 1. O MODELO DE DADOS (O que a API devolve) ---
// No exame, muda os nomes conforme o JSON (ex: "title", "description", "price")
data class Post(
    val id: Int,
    val title: String,
    val description: String
)

// --- 2. A INTERFACE (Os pedidos) ---
interface ApiService {
    // No exame, muda "posts" para o endpoint que pedirem (ex: "products", "users")
    @GET("posts")
    suspend fun getPosts(): List<Post>
}

// --- 3. A INSTÂNCIA (O Singleton) ---
object RetrofitClient {
    // No exame, muda este BASE_URL para o que estiver no enunciado
    private const val BASE_URL = "https://unstats.un.org/sdgapi/v1/sdg/Goal/List?includechildren=true"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}