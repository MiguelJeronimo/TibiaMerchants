package com.miguel.tibiamerchants

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Firebase
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            //agregamos los modulos de koin
            modules(Di().appModule)
        }
    }
}