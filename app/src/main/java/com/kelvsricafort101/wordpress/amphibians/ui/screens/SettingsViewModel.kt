package com.kelvsricafort101.wordpress.amphibians.ui.screens

import com.kelvsricafort101.wordpress.amphibians.data.AppLanguage

data class SettingsUiState(
    val darkMode: Boolean = false,
    val appLanguage: AppLanguage = AppLanguage.ENGLISH
)
class SettingsViewModel {
}