package sazhin.pp.filmcomposableapp.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import sazhin.pp.filmcomposableapp.data.api.FilmApi
import sazhin.pp.filmcomposableapp.data.mapper.FilmResponseToEntityMapper
import sazhin.pp.filmcomposableapp.domain.IFilmRepository
import sazhin.pp.filmcomposableapp.domain.models.Film

class FilmRepository(
    private val api: FilmApi,
    private val mapper: FilmResponseToEntityMapper
) : IFilmRepository {
    override suspend fun getFilms(nameSearch: String): List<Film> {
        return withContext(Dispatchers.IO) {
            mapper.mapFilms(api.getFilms("JQNKTEJ-7BJ46J2-GP2VN66-NTVDF2C",1, 10, nameSearch))
        }
    }
}