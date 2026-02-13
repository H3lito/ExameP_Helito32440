package com.example.examep_hlito_32440

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.example.examep_hlito_32440.ui.theme.ExameP_Hélito_32440Theme
import com.example.examep_hlito_32440.ui.theme.components.screens.homeScreen
import com.example.examep_hlito_32440.ui.theme.components.screens.homeViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        val  homeViewModel: homeViewModel = ViewModel()
        setContent {
            ExameP_Hélito_32440Theme {

                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text("ODS") })
                    }
                ) { padding ->
                    homeScreen(
                        homeUiState = homeViewModel.syncDados(),
                        modifier = Modifier.padding(padding)
                    )
                }

                }
            }
        }
    }


