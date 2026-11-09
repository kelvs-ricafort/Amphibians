package com.kelvsricafort101.wordpress.amphibians.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kelvsricafort101.wordpress.amphibians.R
import com.kelvsricafort101.wordpress.amphibians.data.AppLanguage
import com.kelvsricafort101.wordpress.amphibians.navigation.NavigationDestination
import com.kelvsricafort101.wordpress.amphibians.ui.AmphibiansTopAppBar
import com.kelvsricafort101.wordpress.amphibians.ui.theme.AmphibiansTheme

object SettingsDestination: NavigationDestination {
    override val route = "settings"
    override val titleRes = R.string.settings
}

@Composable
fun SettingsScreen(
    darkMode: Boolean,
    onDarkModeChanged: (Boolean) -> Unit,
    selectedLanguage: AppLanguage,
    onLanguageSelected: (AppLanguage) -> Unit,
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AmphibiansTopAppBar(
                openDrawer = openDrawer,
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .padding(dimensionResource(R.dimen.padding_medium)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
        ) {
            Text(
                text = stringResource(R.string.appearance),
                style = MaterialTheme.typography.titleLarge
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = stringResource(R.string.dark_mode),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = stringResource(R.string.dark_mode_description),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Switch(
                    checked = darkMode,
                    onCheckedChange = onDarkModeChanged
                )
            }

            HorizontalDivider()

            Text(
                text = stringResource(R.string.language),
                style = MaterialTheme.typography.titleLarge
            )
            LanguageSelector(
                selectedLanguage = selectedLanguage,
                onLanguageSelected = onLanguageSelected
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    AmphibiansTheme(darkTheme = false) {
        Surface {
            SettingsScreen(
                darkMode = false,
                onDarkModeChanged = {},
                selectedLanguage = AppLanguage.ENGLISH,
                onLanguageSelected = {},
                openDrawer = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenDarkPreview() {
    AmphibiansTheme(darkTheme = true) {
        Surface {
            SettingsScreen(
                darkMode = true,
                onDarkModeChanged = {},
                selectedLanguage = AppLanguage.ENGLISH,
                onLanguageSelected = {},
                openDrawer = {}
            )
        }
    }
}

