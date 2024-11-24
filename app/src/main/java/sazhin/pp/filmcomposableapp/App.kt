package sazhin.pp.filmcomposableapp

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import sazhin.pp.filmcomposableapp.di.dbModule
import sazhin.pp.filmcomposableapp.di.networkModule
import sazhin.pp.filmcomposableapp.di.rootModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(rootModule, networkModule, dbModule)
        }
    }
}