package sazhin.pp.filmcomposableapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import sazhin.pp.filmcomposableapp.di.dbModule
import sazhin.pp.filmcomposableapp.di.networkModule
import sazhin.pp.filmcomposableapp.di.rootModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(rootModule, networkModule, dbModule)
        }
    }
}