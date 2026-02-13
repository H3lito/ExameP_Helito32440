package com.example.examep_hlito_32440.ui.theme.components.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.examep_hlito_32440.ui.theme.components.datas.DadoEntity

@Composable
fun homeScreen(
    homeUiState: Unit,
    modifier: Modifier = Modifier
) {
    when (homeUiState) {
        is homeUiState.Loading -> LoadingScreen(modifier)
        is homeUiState.Success -> ListScreen(homeUiState.listods, modifier)
        is homeUiState.Error -> ErrorScreen(modifier)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center)
    {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center)
    {
        Text("Erro ao carregar roupas")
    }
}

@Composable
fun ListScreen(listods: List<DadoEntity>, modifier: Modifier =
    Modifier) {
    LazyColumn(modifier = modifier.padding(16.dp)) {
        items(listods) { lista ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                Row(modifier = Modifier.padding(12.dp)) {
                    Column {
                        Text(text = lista.title)
                        Text(text = lista.description)
                    }
                }
            }
        }
    }
}