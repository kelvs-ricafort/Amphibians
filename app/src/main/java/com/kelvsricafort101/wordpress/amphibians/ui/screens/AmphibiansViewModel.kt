package com.kelvsricafort101.wordpress.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.kelvsricafort101.wordpress.amphibians.AmphibiansApplication
import com.kelvsricafort101.wordpress.amphibians.data.AmphibiansRepository
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
class AmphibiansViewModel(
    private val amphibiansRepository: AmphibiansRepository
): ViewModel() {
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
            amphibiansUiState = AmphibiansUiState.Loading
            amphibiansUiState = try {
                val listResult = amphibiansRepository.getAmphibians()
                AmphibiansUiState.Success(
                    "Success: ${listResult.size} Amphibians retrieved"
                )
            } catch (_: IOException) {
                AmphibiansUiState.Error
            } catch (_: HttpException) {
                AmphibiansUiState.Error
            }
        }
    }

    /**
     * Factory for [AmphibiansViewModel] that takes [AmphibiansRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibiansApplication)
                val amphibiansRepository = application.container.amphibiansRepository
                AmphibiansViewModel(amphibiansRepository = amphibiansRepository)
            }
        }
    }
}