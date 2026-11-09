package com.kelvsricafort101.wordpress.amphibians.ui

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kelvsricafort101.wordpress.amphibians.R
import com.kelvsricafort101.wordpress.amphibians.navigation.AmphibiansNavHost
import com.kelvsricafort101.wordpress.amphibians.ui.screens.AmphibiansUiState
import com.kelvsricafort101.wordpress.amphibians.ui.screens.SettingsDataStore
import com.kelvsricafort101.wordpress.amphibians.ui.screens.SettingsViewModel
import com.kelvsricafort101.wordpress.amphibians.ui.screens.SettingsViewModelFactory
import com.kelvsricafort101.wordpress.amphibians.ui.theme.AmphibiansTheme
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun AmphibiansApp(
    amphibiansUiState: AmphibiansUiState,
    retryAction: () -> Unit,
    navController: NavHostController = rememberNavController(),
) {
    val context = LocalContext.current
    val currentConfiguration = LocalConfiguration.current

    val settingsDataStore = remember {
        SettingsDataStore(context.applicationContext)
    }

    val settingsViewModel: SettingsViewModel = viewModel(
        factory = SettingsViewModelFactory(
            settingsDataStore = settingsDataStore
        )
    )

    val settingsUiState by settingsViewModel.uiState.collectAsState()

    val configuration = remember(
        settingsUiState.appLanguage.code,
        currentConfiguration
    ) {
        Configuration(currentConfiguration).apply {
            setLocale(
                Locale.forLanguageTag(settingsUiState.appLanguage.code)
            )
        }
    }

    val localizedContext = remember(configuration) {
        context.createConfigurationContext(configuration)
    }

    // Navigation Drawer State
    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

    val scope = rememberCoroutineScope()
    val openDrawer: () -> Unit = {
        scope.launch {
            drawerState.open()
        }
    }

    CompositionLocalProvider(
        LocalContext provides localizedContext,
        LocalConfiguration provides configuration
    ) {
        AmphibiansTheme(
            darkTheme = settingsUiState.darkMode
        ) {
            AmphibiansNavHost(
                navController = navController,
                amphibiansUiState = amphibiansUiState,
                retryAction = retryAction,
                openDrawer = openDrawer,
                darkMode = settingsUiState.darkMode,
                onDarkModeChanged = settingsViewModel::setDarkMode,
                selectedLanguage = settingsUiState.appLanguage,
                onLanguageSelected = settingsViewModel::setLanguage
            )
        }
    }
}

@Composable
fun AmphibiansTopAppBar(
    openDrawer: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            IconButton(
                onClick = openDrawer
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = stringResource(R.string.open_navigation_drawer)
                )
            }
        },
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall
            )
        },
        modifier = modifier
    )
}