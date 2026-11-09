package com.kelvsricafort101.wordpress.amphibians

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kelvsricafort101.wordpress.amphibians.ui.AmphibiansApp
import com.kelvsricafort101.wordpress.amphibians.ui.screens.AmphibiansViewModel
import com.kelvsricafort101.wordpress.amphibians.ui.theme.AmphibiansTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val amphibiansViewModel: AmphibiansViewModel = viewModel(
                factory = AmphibiansViewModel.Factory
            )
            AmphibiansTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AmphibiansApp(
                        amphibiansUiState = amphibiansViewModel.amphibiansUiState,
                        retryAction = amphibiansViewModel::getAmphibians,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AmphibiansAppPreview() {
    AmphibiansTheme(darkTheme = false) {
        val amphibiansViewModel: AmphibiansViewModel = viewModel(
            factory = AmphibiansViewModel.Factory
        )
        Surface {
            AmphibiansApp(
                amphibiansUiState = amphibiansViewModel.amphibiansUiState,
                retryAction = amphibiansViewModel::getAmphibians
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AmphibiansAppDarkPreview() {
    AmphibiansTheme(darkTheme = true) {
        val amphibiansViewModel: AmphibiansViewModel = viewModel(
            factory = AmphibiansViewModel.Factory
        )
        Surface {
            AmphibiansApp(
                amphibiansUiState = amphibiansViewModel.amphibiansUiState,
                retryAction = amphibiansViewModel::getAmphibians
            )
        }
    }
}