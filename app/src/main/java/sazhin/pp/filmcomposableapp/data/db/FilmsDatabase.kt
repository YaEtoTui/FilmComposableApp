package sazhin.pp.filmcomposableapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import sazhin.pp.filmcomposableapp.data.dao.FilmsDao
import sazhin.pp.filmcomposableapp.data.model.entity.FilmDbEntity

@Database(entities = [FilmDbEntity::class], version = 1)
abstract class FilmsDatabase : RoomDatabase() {
    abstract fun filmsDao(): FilmsDao
}