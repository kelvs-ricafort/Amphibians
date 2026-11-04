package com.kelvsricafort101.wordpress.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kelvsricafort101.wordpress.amphibians.network.AmphibiansApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * UI state for the Home screen
 */
sealed interface AmphibiansUiState {
    data class Success(val amphibians: String): AmphibiansUiState
    object Error: AmphibiansUiState
    object Loading: AmphibiansUiState
}
/**
 * ViewModel containing the app data and method to retrieve the data
 */
class AmphibiansViewModel: ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var amphibiansUiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
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
        viewModelScope.launch {
            try {
                val listResult = AmphibiansApi.retrofitService.getAmphibians()
                amphibiansUiState = AmphibiansUiState.Success(
                    "Success: ${listResult.size} Amphibians retrieved"
                )
            } catch (_: IOException) {
                amphibiansUiState = AmphibiansUiState.Error
            } catch (_: HttpException) {
                amphibiansUiState = AmphibiansUiState.Error
            }
        }
    }
}