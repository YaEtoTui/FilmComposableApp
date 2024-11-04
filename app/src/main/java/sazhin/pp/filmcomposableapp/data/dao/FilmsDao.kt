package sazhin.pp.filmcomposableapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import sazhin.pp.filmcomposableapp.data.model.entity.FilmDbEntity

@Dao
interface FilmsDao {

    @Query("SELECT * FROM FilmDbEntity")
    suspend fun getAll(): List<FilmDbEntity>

    @Insert
    suspend fun insert(filmDbEntity: FilmDbEntity)
}