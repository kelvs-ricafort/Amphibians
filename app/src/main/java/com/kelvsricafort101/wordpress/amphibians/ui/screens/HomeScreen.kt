package com.kelvsricafort101.wordpress.amphibians.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kelvsricafort101.wordpress.amphibians.R
import com.kelvsricafort101.wordpress.amphibians.ui.theme.AmphibiansTheme

@Composable
fun HomeScreen(
    amphibiansUiState: String,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    ResultScreen(
        amphibians = amphibiansUiState,
        modifier = modifier.padding(
            top = contentPadding.calculateTopPadding()
        )
    )
}

/**
 * ResultScreen displaying number of amphibians retrieved.
 */
@Composable
fun ResultScreen(
    amphibians: String,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(
            text = amphibians
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    AmphibiansTheme(darkTheme = false) {
        Surface {
            ResultScreen(
                amphibians = stringResource(R.string.placeholder_result)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenDarkPreview() {
    AmphibiansTheme(darkTheme = true) {
        Surface {
            ResultScreen(
                amphibians = stringResource(R.string.placeholder_result)
            )
        }
    }
}