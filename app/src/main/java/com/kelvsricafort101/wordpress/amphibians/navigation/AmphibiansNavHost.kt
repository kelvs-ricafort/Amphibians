package com.kelvsricafort101.wordpress.amphibians.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kelvsricafort101.wordpress.amphibians.data.AppLanguage
import com.kelvsricafort101.wordpress.amphibians.ui.screens.AboutAppDestination
import com.kelvsricafort101.wordpress.amphibians.ui.screens.AboutAppScreen
import com.kelvsricafort101.wordpress.amphibians.ui.screens.AmphibiansUiState
import com.kelvsricafort101.wordpress.amphibians.ui.screens.HomeDestination
import com.kelvsricafort101.wordpress.amphibians.ui.screens.HomeScreen
import com.kelvsricafort101.wordpress.amphibians.ui.screens.SettingsDestination
import com.kelvsricafort101.wordpress.amphibians.ui.screens.SettingsScreen

/**
 * Provides Navigation Graph for the app.
 */
@Composable
fun AmphibiansNavHost(
    navController: NavHostController = rememberNavController(),
    amphibiansUiState: AmphibiansUiState,
    retryAction: () -> Unit,
    openDrawer: () -> Unit,
    darkMode: Boolean,
    selectedLanguage: AppLanguage,
    onDarkModeChanged: (Boolean) -> Unit,
    onLanguageSelected: (AppLanguage) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    AmphibianNavigationDrawer(
        selectedRoute = currentRoute,
        onNavigate = { route ->
            navController.navigate(route) {
                popUpTo(HomeDestination.route) {
                    saveState = true
                }

                launchSingleTop = true
                restoreState = true
            }
        }
    ) { openDrawer ->
        NavHost(
            navController = navController,
            startDestination = HomeDestination.route,
            modifier = modifier
        ) {
            composable(route = HomeDestination.route) {
                HomeScreen(
                    amphibiansUiState = amphibiansUiState,
                    retryAction = retryAction,
                    openDrawer = openDrawer,
                    contentPadding = contentPadding
                )
            }

            composable(route = SettingsDestination.route) {
                SettingsScreen(
                    darkMode = darkMode,
                    onDarkModeChanged = onDarkModeChanged,
                    selectedLanguage = selectedLanguage,
                    onLanguageSelected = onLanguageSelected,
                    openDrawer = openDrawer
                )
            }

            composable(route = AboutAppDestination.route) {
                AboutAppScreen(
                    modifier = modifier,
                    openDrawer = openDrawer
                )
            }
        }
    }
}