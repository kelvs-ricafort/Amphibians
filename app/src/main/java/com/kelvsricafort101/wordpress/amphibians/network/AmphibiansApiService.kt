package com.kelvsricafort101.wordpress.amphibians.network

import com.kelvsricafort101.wordpress.amphibians.model.Amphibian
import retrofit2.http.GET

/**
 * Retrofit service object for creating api calls
 */
interface AmphibiansApiService {
    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibian>
}