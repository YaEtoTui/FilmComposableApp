package sazhin.pp.filmcomposableapp.di

import android.content.Context
import androidx.room.Room
import org.koin.dsl.module
import sazhin.pp.filmcomposableapp.data.db.FilmsDatabase
import java.util.Objects.isNull

val dbModule = module {
    single { DataBaseBuilder.getInstance(get()) }
}

object DataBaseBuilder {

    private var INSTANCE: FilmsDatabase? = null

    fun getInstance(context: Context): FilmsDatabase {
        if (isNull(INSTANCE)) {
            synchronized(FilmsDatabase::class) {
                INSTANCE = buildRoomDB(context)
            }
        }

        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            FilmsDatabase::class.java,
            "films-favorite"
        ).build()
}