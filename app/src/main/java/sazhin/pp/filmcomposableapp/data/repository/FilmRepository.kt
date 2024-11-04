package sazhin.pp.filmcomposableapp.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import sazhin.pp.filmcomposableapp.data.api.FilmApi
import sazhin.pp.filmcomposableapp.data.db.FilmsDatabase
import sazhin.pp.filmcomposableapp.data.mapper.FilmResponseToEntityMapper
import sazhin.pp.filmcomposableapp.data.model.entity.FilmDbEntity
import sazhin.pp.filmcomposableapp.domain.IFilmRepository
import sazhin.pp.filmcomposableapp.domain.models.Film

class FilmRepository(
    private val api: FilmApi,
    private val filmDb: FilmsDatabase,
    private val mapper: FilmResponseToEntityMapper
) : IFilmRepository {

    override suspend fun getFilms(nameSearch: String, limit: Int): List<Film> {
        return withContext(Dispatchers.IO) {
            mapper.mapFilms(api.getFilms("JQNKTEJ-7BJ46J2-GP2VN66-NTVDF2C",1, limit, nameSearch))
        }
    }

    override suspend fun saveFilm(film: Film) {
        return withContext(Dispatchers.IO) {
            filmDb.filmsDao().insert(
                FilmDbEntity(
                    name = film.name,
                    year = film.year,
                    pathPhoto = film.photo,
                    description = film.description
                )
            )
        }
    }

    override suspend fun getFavoriteFilms(): List<Film> {
        return withContext(Dispatchers.IO) {
            filmDb.filmsDao()
                .getAll()
                .map {
                    Film(
                        id = it.id ?: 0L,
                        name = it.name.orEmpty(),
                        photo = it.pathPhoto.orEmpty(),
                        type = "",
                        year = it.year ?: 0,
                        description = it.description.orEmpty(),
                        shortDescription = "",
                        rating = 0.0,
                        movieLength = 0,
                    )
                }
        }
    }
}