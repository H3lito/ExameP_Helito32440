package com.example.examep_hlito_32440.ui.theme.components.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.examep_hlito_32440.ui.theme.components.datas.AppDatabase
import com.example.examep_hlito_32440.ui.theme.components.datas.DadoEntity
import com.example.examep_hlito_32440.ui.theme.components.datas.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class homeViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    private val dao = db.dao()

    // Estado da UI (Vem direto da BD Local = Offline First)
    val dados: StateFlow<List<DadoEntity>> = dao.getAll()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Função para carregar da API e guardar na BD
    fun syncDados() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // 1. Buscar à API
                val apiItems = RetrofitClient.api.getDados()

                // 2. Converter para Entidade Room
                val entidades = apiItems.map {
                    DadoEntity(code = it.code, title = it.title, description = it.description)
                }

                // 3. Guardar na BD (A UI atualiza sozinha por causa do Flow)
                dao.insertAll(entidades)

            } catch (e: Exception) {
                e.printStackTrace() // Sem net? Não faz mal, mostra o que tem na BD.
            }
        }
    }
}