package com.kelvsricafort101.wordpress.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 * ViewModel containing the app data and method to retrieve the data
 */
class AmphibiansViewModel: ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var amphibiansUiState: String by mutableStateOf("")
        private set

    /**
     * Call getAmphibians() on init so we can display status immediately.
     */
    init {
        getAmphibians()
    }

    /**
     * Gets Amphibian information from the Amphibian API Retrofit service and updates the
     * [Amphibian] [List] [MutableList].
     */
    fun getAmphibians() {
        amphibiansUiState = "Set the Amphibian API Status Response here!"
    }
}