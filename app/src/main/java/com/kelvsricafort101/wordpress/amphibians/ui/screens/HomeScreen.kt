package com.kelvsricafort101.wordpress.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kelvsricafort101.wordpress.amphibians.R
import com.kelvsricafort101.wordpress.amphibians.ui.theme.AmphibiansTheme

@Composable
fun HomeScreen(
    amphibiansUiState: AmphibiansUiState,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    when(amphibiansUiState) {
        is AmphibiansUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is AmphibiansUiState.Success -> ResultScreen(
            amphibians = amphibiansUiState.amphibians,
            modifier = modifier.padding(
                top = contentPadding.calculateTopPadding()
            )
        )
        is AmphibiansUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
    }
}

/**
 * The home screen displaying the loading message.
 */
@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier.size(dimensionResource(R.dimen.image_size)),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

/**
 * The home screen displaying error message with a re-attempt button.
 */
@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_connection_error),
            contentDescription = stringResource(R.string.loading_failed)
        )
        Text(
            text = stringResource(R.string.loading_failed),
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        )
    }
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

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    AmphibiansTheme(darkTheme = false) {
        Surface {
            LoadingScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenDarkPreview() {
    AmphibiansTheme(darkTheme = true) {
        Surface {
            LoadingScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    AmphibiansTheme(darkTheme = false) {
        Surface {
            ErrorScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenDarkPreview() {
    AmphibiansTheme(darkTheme = true) {
        Surface {
            ErrorScreen()
        }
    }
}
