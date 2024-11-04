package sazhin.pp.filmcomposableapp.domain

import sazhin.pp.filmcomposableapp.domain.models.Film

interface IFilmRepository {
    suspend fun getFilms(nameSearch: String, limit: Int): List<Film>
    suspend fun saveFilm(film: Film)
    suspend fun getFavoriteFilms(): List<Film>
}