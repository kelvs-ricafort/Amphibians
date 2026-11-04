package com.kelvsricafort101.wordpress.amphibians

import android.app.Application
import com.kelvsricafort101.wordpress.amphibians.data.AppContainer
import com.kelvsricafort101.wordpress.amphibians.data.DefaultAppContainer

class AmphibiansApplication: Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}