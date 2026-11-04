package com.kelvsricafort101.wordpress.amphibians.data

import com.kelvsricafort101.wordpress.amphibians.model.Amphibian
import com.kelvsricafort101.wordpress.amphibians.network.AmphibiansApiService

/**
 * Repository retrieves amphibian data from underlying data source.
 */
interface AmphibiansRepository {
    /**
     * Retrieves list of amphibian data from underlying data source.
     */
    suspend fun getAmphibians(): List<Amphibian>
}

/**
 * Network Implementation of repository that retrieves amphibian data from underlying data source.
 */
class NetworkAmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService
): AmphibiansRepository {
    override suspend fun getAmphibians(): List<Amphibian> = amphibiansApiService.getAmphibians()
}