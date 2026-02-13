package com.example.examep_hlito_32440.ui.theme.components.screens

import com.example.examep_hlito_32440.ui.theme.components.datas.DadoEntity

sealed interface  homeUiState{
    data class Success(val listods: List<DadoEntity>) : homeUiState
    object Error : homeUiState
    object Loading : homeUiState
}